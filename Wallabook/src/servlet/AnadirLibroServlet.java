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
public class AnadirLibroServlet  {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();

    /**
     * Default constructor. 
     */
    public AnadirLibroServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		//String editorial = request.getParameter("editorial");
		String idioma = request.getParameter("idioma");
		byte disponible = 1;
		// TODO es un boolean
		String categoriaInput = request.getParameter("categoria");
		Categoria categoria = wallabookDAO.consultarCategoriaNombre(categoriaInput);
		// TODO aqui queremos un desplegable
		//Libro libro = new Libro (titulo, autor, editorial, idioma, disponible, id_categoria);
		String nickname = request.getParameter("usr");
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		Libro libro = new Libro (autor, disponible, idioma,titulo, categoria, usuario);
		wallabookDAO.anadirLibro(libro);	
		
	}

}
