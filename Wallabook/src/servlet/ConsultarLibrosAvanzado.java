package servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ConsultarLibrosAutorServlet
 */
@WebServlet("/ConsultarLibrosAvanzado")
public class ConsultarLibrosAvanzado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarLibrosAvanzado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Libro> libros = null;
		String titulo =  " ";
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);		
		String autor = " ";
		String categoriaInput = " ";		
			if (titulo!=" ") {
				titulo = request.getParameter("titulo");
			}
			if (autor!=" ") {
				autor = request.getParameter("autor");
			}
			if (categoriaInput!=" ") {			
				categoriaInput = request.getParameter("categoria");
				Categoria categoria = wallabookDAO.consultarCategoriaNombre(categoriaInput);
			}
		libros = wallabookDAO.buscarLibrosAvanzado(titulo, autor, categoriaInput, usuario);		
		request.getRequestDispatcher("/MostrarLibros/mostrarLibros.jsp").forward(request, response);
		
	}	
}
