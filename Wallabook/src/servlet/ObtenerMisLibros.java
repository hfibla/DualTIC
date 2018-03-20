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
 * Servlet implementation class ObtenerMisLibros
 */
@WebServlet("/ObtenerMisLibros")
public class ObtenerMisLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerMisLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
	    String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(usuario);
		if (wallabookDAO.numLibrosNoDisponiblesUsuario(usuario) == 5) {
			request.setAttribute("maxLibros", "Has alcanzado el límite de libros no disponibles. No podrás pedir libros hasta que reduzcas esa cantidad.");
		}
		request.setAttribute("usuario", usuario);
	    request.setAttribute("libros", libros);
	    request.getRequestDispatcher("/MostrarMisLibros/misLibros.jsp").forward(request, response);
	}

}
