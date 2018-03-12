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
 * Servlet implementation class ObtenerLibrosUsuario
 */
@WebServlet("/ObtenerLibrosUsuario")
public class ObtenerLibrosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerLibrosUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WallabookDAO wallabookDAO = new WallabookDAO();
		String miNickname = (String) request.getSession(false).getAttribute("me");
		Usuario miUsuario =  wallabookDAO.consultarUsuarioNickname(miNickname);
	    String suNickname = request.getParameter("usr");
		Usuario suUsuario =  wallabookDAO.consultarUsuarioNickname(suNickname);
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(suUsuario);
		request.setAttribute("miUsuario", miUsuario);
		request.setAttribute("suUsuario", suUsuario);
	    request.setAttribute("libros", libros);
	    request.getRequestDispatcher("/MostrarLibrosUsuario/mostrarLibrosUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
