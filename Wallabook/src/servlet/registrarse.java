package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import model.WallabookDAO;

/**
 * Servlet implementation class registrarse
 */
@WebServlet("/registrarse")
public class registrarse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	WallabookDAO wallabookDAO = new WallabookDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrarse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    	PrintWriter out = response.getWriter();
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String localidad = request.getParameter("localidad");
		String correo = request.getParameter("correo");
		Usuario usuario = new Usuario(correo, localidad, nickname, password);
		wallabookDAO.registrarUsuario(usuario);
		out.println("El usuario " + nickname + " ha sido insertado correctamente");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
