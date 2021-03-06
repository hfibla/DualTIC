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
 * Servlet implementation class Registrarse
 */
@WebServlet("/Registro/registrarse")
public class Registrarse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrarse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    	WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String localidad = request.getParameter("localidad");
		String correo = request.getParameter("correo");
		Usuario usuario = new Usuario(correo, localidad, nickname, password);
		if (wallabookDAO.registrarUsuario(usuario)) {
			response.sendRedirect("/Wallabook/LogIn/index.html?registro=1");
		}
		else {
			response.sendRedirect("/Wallabook/Registro/index.html?registro=0");
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
