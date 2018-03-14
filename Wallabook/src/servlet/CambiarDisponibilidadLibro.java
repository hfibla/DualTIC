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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarDisponibilidadLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		String[] disponibilidadLibros = request.getParameterValues("disponible");
		int librosNoDisponibles = wallabookDAO.consultarLibrosUsuario(usuario).size() - disponibilidadLibros.length;
		if (librosNoDisponibles <= 5) {
			wallabookDAO.cambiarDisponibilidadLibros(disponibilidadLibros, usuario);
		}
		else {
			request.setAttribute("error", 1);
		}
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(usuario);
		request.setAttribute("usuario", usuario);
	    request.setAttribute("libros", libros);
	    request.getRequestDispatcher("/MostrarMisLibros/misLibros.jsp").forward(request, response);
	}

}
