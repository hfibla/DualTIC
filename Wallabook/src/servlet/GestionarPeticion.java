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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		Libro libro = wallabookDAO.consultarLibroID(request.getParameter("idlibro"));
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario antiguoPropietario = wallabookDAO.consultarUsuarioNickname(nickname);
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(antiguoPropietario);
		String mensaje = "";
		if (request.getParameter("peticion").equals("aceptar")) {
			mensaje = "Se ha aceptado la petición";
			Usuario nuevoPropietario = wallabookDAO.consultarUsuarioNickname(request.getParameter("idRemitente"));
			if (wallabookDAO.cambiarPropietarioLibro(libro, antiguoPropietario, nuevoPropietario)) {
				wallabookDAO.enviarNotificacionesCambioLibroOK(libro, antiguoPropietario, nuevoPropietario);
				wallabookDAO.enviarNotificacionesOtrosCambioLibroDenegado(libro, nuevoPropietario);
			} else {
				wallabookDAO.enviarNotificacionesCambioLibroError(libro, antiguoPropietario, nuevoPropietario);
			}
		} else if (request.getParameter("peticion").equals("denegar")) {
			Usuario usuario = wallabookDAO.consultarUsuarioNickname(request.getParameter("idRemitente"));
			wallabookDAO.denegarPeticion(libro, usuario);
			mensaje = "Se ha denegado la petición";
		} else {
			mensaje = "Ha ocurrido un error";
		}
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("usuario", antiguoPropietario);
		request.setAttribute("libros", libros);
		request.getRequestDispatcher("/MostrarMisLibros/misLibros.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
