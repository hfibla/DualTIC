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
		String titulo = request.getParameter("titulo");
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		libros = wallabookDAO.buscarLibrosAvanzado();
		String autor = request.getParameter("autor");
		String categoriaInput = request.getParameter("categoria");
		Categoria categoria = wallabookDAO.consultarCategoriaNombre(categoriaInput);
		if (titulo!=null) {
			
		}
		
		
		//List<Libro> libro = wallabookDAO.consultarLibrosAutor(autor);		
		//rederigir a jsp ()
		request.getRequestDispatcher("/MostrarLibros/mostrarLibros.jsp").forward(request, response);
		
	}

}
