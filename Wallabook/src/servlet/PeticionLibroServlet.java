package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Libro;
import model.Notificacion;
import model.Peticion;
import model.Usuario;

/**
 * Servlet implementation class PeticionLibroServlet
 */
@WebServlet("/peticionLibro")
public class PeticionLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeticionLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario usuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		Libro libro = wallabookDAO.consultarLibroID(request.getParameter("idLibro"));		
		wallabookDAO.crearPeticionLibro(new Peticion(libro,usuario));
		wallabookDAO.crearNotificacionPeticionLibro(new Notificacion(usuario.getNickname() + " desea tu libro " + libro.getTitulo(), libro.getUsuario()));
		request.setAttribute("miUsuario", usuario);
		request.setAttribute("suUsuario", libro.getUsuario());
	    request.setAttribute("libros", libro.getUsuario().getLibros());
	    if (wallabookDAO.numLibrosNoDisponiblesUsuario(usuario) == 5) {
	    	request.setAttribute("noPuedePedir", "1");
	    }
		request.getRequestDispatcher("/MostrarLibrosUsuario/mostrarLibrosUsuario.jsp").forward(request, response);
		}		
	}


