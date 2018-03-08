package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Categoria;
import model.Libro;
import model.Usuario;

/**
 * Servlet implementation class AnadirLibroServlet
 */
@WebServlet("/AnadirLibros/anadir")
public class AnadirLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();

    /**
     * Default constructor. 
     */
    public AnadirLibroServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Libro libro = null;
    	String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String idioma = request.getParameter("idioma");
		int disponible = 1;
		// es un boolean
		String categoriaInput = request.getParameter("categoria");
		Categoria categoria = wallabookDAO.consultarCategoriaNombre(categoriaInput);
		// aqui queremos un desplegable
		String nickname = (String) request.getSession(false).getAttribute("me");
		System.out.println(nickname);
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		if (request.getParameterMap().containsKey("editorial")) {
			String editorial = request.getParameter("editorial");
			libro = new Libro (autor, disponible, editorial, idioma, titulo, categoria, usuario);
        } else {
        	libro = new Libro (autor, disponible, idioma, titulo, categoria, usuario);
        }
		wallabookDAO.anadirLibro(libro);	
		
	}
    
    

}
