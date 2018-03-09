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
 * Servlet implementation class Login
 */
@WebServlet("/LogIn/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    WallabookDAO wallabookDAO = new WallabookDAO();
	    String nickname = request.getParameter("nickname");
	    String password = request.getParameter("password");		
	    Usuario usuario = new Usuario(nickname, password);
	    if (wallabookDAO.comprobarLogin(usuario.getNickname(), usuario.getPassword())) {
	    	request.getSession(true).setAttribute("me", usuario.getNickname());
	    	response.sendRedirect("/Wallabook/PaginaPrincipal");
	    }
	    else {
	    	response.sendRedirect("/Wallabook/LogIn/index.html?registro=0");
	    }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
