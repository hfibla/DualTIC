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
 * Servlet implementation class ObtenerDisponibilidadMisLibros
 */
@WebServlet("/ObtenerDisponibilidadMisLibros")
public class ObtenerDisponibilidadMisLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerDisponibilidadMisLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		List<Libro> libros = wallabookDAO.consultarLibrosUsuario(usuario);
		request.setAttribute("usuario", usuario);
	    request.setAttribute("libros", libros);
	    if (wallabookDAO.numLibrosNoDisponiblesUsuario(usuario) > 5) {
	    	request.setAttribute("error", 1);
	    }
	    request.getRequestDispatcher("/CambiarDisponibilidadMisLibros/cambiarDisponibilidad.jsp").forward(request, response);
	}

	

}
