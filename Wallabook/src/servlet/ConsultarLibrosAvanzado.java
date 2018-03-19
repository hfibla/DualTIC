package servlet;

import java.io.IOException;
import java.util.Collections;
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
		List <Categoria> categoriaInput = wallabookDAO.obtenerCategorias();
		request.setAttribute("categorias", categoriaInput);	
	}
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String espacio = "";
		String titulo =  "";
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);		
		String autor = "";
		String categoriaInput = "";		
		
			if (request.getParameter("titulo").equals(espacio)) {
				titulo = "%";
				}
			else {
				titulo = request.getParameter(titulo);
			}
			
			if (request.getParameter("autor").equals(espacio)) {
				autor = "%";
				}
			else {
				autor = request.getParameter(autor);
			}
			if (request.getParameter("categoriaInput").equals(espacio)) {
				categoriaInput = "%";
			}
			else {
				categoriaInput= request.getParameter(categoriaInput);
			}
			List<Libro> libros = wallabookDAO.buscarLibrosAvanzado(titulo, autor, categoriaInput, usuario);
			
			if (libros.isEmpty()) {
				String mensajeNullLibros = "No hemos encontrado ningún libro con ese título.";
				request.setAttribute("error", mensajeNullLibros);
			}
			else {
			request.setAttribute("libros", libros);		
			}
			request.getRequestDispatcher("/MostrarLibros/mostrarLibros.jsp").forward(request, response);				
	}	
}
