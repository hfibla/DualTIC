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
 * Servlet implementation class EliminarCuenta
 */
@WebServlet("/EliminarCuenta")
public class EliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public EliminarCuenta() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		wallabookDAO.eliminarUsuario(usuario);
		request.getSession().invalidate();
    	response.sendRedirect("/Wallabook/LogIn/index.html?eliminar=1");
	}

}
