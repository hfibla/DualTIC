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
		wallabookDAO.peticionLibro(new Peticion(libro,usuario));
		wallabookDAO.notificacionPeticionLibro(new Notificacion("Usuario quiere libro", libro.getUsuario()));
		response.sendRedirect("/MostrarLibros/mostrarLibros.jsp");
		}		
	}


