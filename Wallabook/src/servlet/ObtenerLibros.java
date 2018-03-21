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
 * Servlet implementation class ObtenerLibros
 */
@WebServlet("/ObtenerLibros")
public class ObtenerLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ObtenerLibros() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String miNickname = (String) request.getSession(false).getAttribute("me");
		Usuario miUsuario =  wallabookDAO.consultarUsuarioNickname(miNickname);
		if (request.getParameterMap().containsKey("usr")) {
	    String suNickname = request.getParameter("usr");
		Usuario suUsuario =  wallabookDAO.consultarUsuarioNickname(suNickname);
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(suUsuario);
		request.setAttribute("miUsuario", miUsuario);
		request.setAttribute("suUsuario", suUsuario);
	    request.setAttribute("libros", libros);
	    if (wallabookDAO.numLibrosNoDisponiblesUsuario(miUsuario) == 5) {
	    	request.setAttribute("noPuedePedir", "1");
	    }
	    request.getRequestDispatcher("/MostrarLibrosUsuario/mostrarLibrosUsuario.jsp").forward(request, response);
		}
		else {
			List<Libro> libros = wallabookDAO.consultarLibrosUsuario(miUsuario);
			if (wallabookDAO.numLibrosNoDisponiblesUsuario(miUsuario) == 5) {
				request.setAttribute("maxLibros", "Has alcanzado el límite de libros no disponibles. No podrás pedir libros hasta que reduzcas esa cantidad.");
			}
			request.setAttribute("usuario", miUsuario);
		    request.setAttribute("libros", libros);
		    request.getRequestDispatcher("/MostrarMisLibros/misLibros.jsp").forward(request, response);
		}
	}

}
