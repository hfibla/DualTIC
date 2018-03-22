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
       
    public PeticionLibroServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario miUsuario =  wallabookDAO.consultarUsuarioNickname(nickname);
		Libro libro = wallabookDAO.consultarLibroID(request.getParameter("idLibro"));
		Usuario suUsuario = libro.getUsuario();
		wallabookDAO.crearPeticionLibro(new Peticion(libro,miUsuario));
		wallabookDAO.crearNotificacionPeticionLibro(new Notificacion(miUsuario.getNickname() + " desea tu libro " + libro.getTitulo(), suUsuario));
		List<Libro> librosSolicitables = wallabookDAO.consultarLibrosPedidoNoPendienteUsuario(miUsuario, suUsuario);
		List<Libro> librosNoSolicitables = wallabookDAO.consultarLibrosPedidoPendienteUsuario(miUsuario, suUsuario);
		request.setAttribute("miUsuario", miUsuario);
		request.setAttribute("suUsuario", suUsuario);
	    request.setAttribute("librosSol", librosSolicitables);
	    request.setAttribute("librosNoSol", librosNoSolicitables);
	    if (wallabookDAO.numLibrosNoDisponiblesUsuario(miUsuario) == 5) {
			request.setAttribute("maxLibros", "Has alcanzado el límite de libros no disponibles. No podrás pedir libros hasta que reduzcas esa cantidad.");
		}
		request.getRequestDispatcher("/MostrarLibrosUsuario/mostrarLibrosUsuario.jsp").forward(request, response);
		}		
	}


