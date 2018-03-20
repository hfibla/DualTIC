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
 * Servlet implementation class ConsultarLibrosAvanzado
 */
@WebServlet("/ConsultarLibrosAvanzado")
public class ConsultarLibrosAvanzado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();

	public ConsultarLibrosAvanzado() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categoria> categorias = wallabookDAO.obtenerCategorias();
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher("/Buscar/BuscarAvanzado.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		String autor = request.getParameter("autor");
		Categoria categoria = new Categoria();
		String categoriaInput = request.getParameter("categoria");
		if (!categoriaInput.equals("default")) {
			categoria = wallabookDAO.consultarCategoriaNombre(categoriaInput);
		}

		List<Libro> libros = wallabookDAO.buscarLibrosAvanzado(titulo, autor, categoria, usuario);
		if (libros.isEmpty()) {
			String mensajeNullLibros = "No hemos encontrado ningún libro con ese título.";
			request.setAttribute("error", mensajeNullLibros);
		} else {
			request.setAttribute("libros", libros);
		}
		request.getRequestDispatcher("/MostrarLibros/mostrarLibros.jsp").forward(request, response);
	}
}
