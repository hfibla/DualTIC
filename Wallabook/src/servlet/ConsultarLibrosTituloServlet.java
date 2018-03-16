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
 * Servlet implementation class ConsultaLibrosServlet
 */
@WebServlet("/ConsultarLibrosTitulo")
public class ConsultarLibrosTituloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarLibrosTituloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		List<Libro> libros = wallabookDAO.buscarLibrosTitulo(titulo, usuario);
		if (libros.isEmpty()) {
			String mensajeNullLibros = "No hemos encontrado ning�n libro con ese t�tulo.";
			request.setAttribute("error", mensajeNullLibros);
		}
		else {
		request.setAttribute("libros", libros);		
		}
		request.getRequestDispatcher("/MostrarLibros/mostrarLibros.jsp").forward(request, response);
	}

}
