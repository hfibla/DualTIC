package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Libro;
import model.Notificacion;
import model.Usuario;

/**
 * Servlet implementation class GestionarPeticion
 */
@WebServlet("/GestionarPeticion")
public class GestionarPeticion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GestionarPeticion() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		int idLibro = Integer.parseInt(request.getParameter("idLibro"));
		Libro libro = wallabookDAO.consultarLibroID(idLibro);
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario antiguoPropietario = wallabookDAO.consultarUsuarioNickname(nickname);
		String mensajeAlert = "";
		if (request.getParameter("peticion").equals("Aceptar")) {
			mensajeAlert = "Se ha aceptado la petición";
			Usuario nuevoPropietario = wallabookDAO.consultarUsuarioNickname(request.getParameter("nickRemitente"));
			if (wallabookDAO.cambiarPropietarioLibro(libro, antiguoPropietario, nuevoPropietario)) {
				wallabookDAO.enviarNotificacionesCambioLibroOK(libro, antiguoPropietario, nuevoPropietario);
				wallabookDAO.enviarNotificacionesOtrosCambioLibroDenegado(libro, nuevoPropietario);
			} else {
				wallabookDAO.enviarNotificacionesCambioLibroError(libro, antiguoPropietario, nuevoPropietario);
			}
		} else if (request.getParameter("peticion").equals("Rechazar")) {
			Usuario usuario = wallabookDAO.consultarUsuarioNickname(request.getParameter("nickRemitente"));
			wallabookDAO.denegarPeticion(libro, usuario);
			mensajeAlert = "Se ha denegado la petición";
		} else {
			mensajeAlert = "Ha ocurrido un error";
		}
		if (mensajeAlert != "") {
			request.setAttribute("alerta", mensajeAlert);
		}
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		List <Notificacion> notificaciones = wallabookDAO.verNotificacionesUsuario(usuario);
		if (notificaciones.isEmpty()) {
			String mensajeNullNotificacion = "No tienes notificaciones";
			request.setAttribute("error", mensajeNullNotificacion);
		}
		else {
		request.setAttribute("notificaciones", notificaciones);
		}
		request.getRequestDispatcher("/accDenPeticiones/verNotificaciones.jsp").forward(request, response);

	}

}
