package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Avatar;
import model.Usuario;

/**
 * Servlet implementation class CambiarAvatar
 */
@WebServlet("/CambiarAvatar")
public class CambiarAvatar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CambiarAvatar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		List<Avatar> avatares = wallabookDAO.obtenerAvatares();
		request.setAttribute("usuario", usuario);
		request.setAttribute("avatares", avatares);
		request.getRequestDispatcher("/CambiarAvatar/cambiarAvatar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		int idAvatar = Integer.parseInt(request.getParameter("avatar"));
		Avatar avatar = new Avatar(idAvatar);
		wallabookDAO.cambiarAvatar(usuario, avatar);
		Usuario usuarioActualizado = wallabookDAO.consultarUsuarioNickname(nickname);
		request.setAttribute("usuario", usuarioActualizado);
		request.getRequestDispatcher("/perfilPropio/miPerfil.jsp").forward(request, response);
	}

}
