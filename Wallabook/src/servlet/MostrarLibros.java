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
 * Servlet implementation class MostrarLibros
 */
@WebServlet("/MostrarLibros")
public class MostrarLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    WallabookDAO wallabookDAO = new WallabookDAO();
	    String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		List<Libro> libros = wallabookDAO.consultarLibrosDisponiblesNoPropios(usuario);
		if (libros.isEmpty()) {
			String mensajeNullLibros = "No hemos encontrado libros.";
			request.setAttribute("error", mensajeNullLibros);
		}
		else {
		request.setAttribute("libros", libros);		
		}
		request.getRequestDispatcher("/MostrarLibros/mostrarLibros.jsp").forward(request, response);
	}

	

}
