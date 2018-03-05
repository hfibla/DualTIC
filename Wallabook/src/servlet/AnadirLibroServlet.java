package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Libro;

/**
 * Servlet implementation class AnadirLibroServlet
 */
@WebServlet("/AnadirLibro")
public class AnadirLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();

    /**
     * Default constructor. 
     */
    public AnadirLibroServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String editorial = request.getParameter("editorial");
		String idioma = request.getParameter("idioma");
		String disponible = request.getParameter("disponible");
		// TODO es un boolean
		String id_categoria = request.getParameter("categoria");
		// TODO aqui queremos un desplegable
		Libro libro = new Libro (titulo, autor, editorial, idioma, disponible, id_categoria);
		wallabookDAO.anadirLibro(libro);
	
		
		
	}

}