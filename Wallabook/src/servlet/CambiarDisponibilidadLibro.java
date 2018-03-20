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
 * Servlet implementation class CambiarDisponibilidadLibro
 */
@WebServlet("/CambiarDisponibilidadLibro")
public class CambiarDisponibilidadLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CambiarDisponibilidadLibro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(usuario);
		request.setAttribute("usuario", usuario);
		request.setAttribute("libros", libros);
		request.getRequestDispatcher("/CambiarDisponibilidadMisLibros/cambiarDisponibilidad.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		String[] disponibilidadLibros = null;
		if (request.getParameterMap().containsKey("disponible")) {
			disponibilidadLibros = request.getParameterValues("disponible");
		} else {
			disponibilidadLibros = new String[0];
		}
		int librosNoDisponibles = wallabookDAO.consultarLibrosUsuario(usuario).size() - disponibilidadLibros.length;
		if (librosNoDisponibles <= 5) {
			wallabookDAO.cambiarDisponibilidadLibros(disponibilidadLibros, usuario);
		} else {
			request.setAttribute("error", "No puedes tener más de 5 libros no disponibles");
		}
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(usuario);
		request.setAttribute("usuario", usuario);
		request.setAttribute("libros", libros);
		request.getRequestDispatcher("/MostrarMisLibros/misLibros.jsp").forward(request, response);
	}

}
