package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Notificacion;
import model.Usuario;

/**
 * Servlet implementation class VerNotificacionesServlet
 */
@WebServlet("/verNotificaciones")
public class VerNotificacionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       WallabookDAO wallabookDAO = new WallabookDAO();
     
    public VerNotificacionesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = (String) request.getSession(false).getAttribute("me");
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
