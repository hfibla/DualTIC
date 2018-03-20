package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Usuario;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/IniciarSesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IniciarSesion() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		Usuario usuario = new Usuario(nickname, password);
		if (wallabookDAO.comprobarLogin(usuario.getNickname(), usuario.getPassword())) {
			request.getSession(true).setAttribute("me", usuario.getNickname());
			response.sendRedirect("/Wallabook/PaginaPrincipal");
		} else {
			response.sendRedirect("/Wallabook/LogIn/index.html?registro=0");
		}

	}

}
