package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Libro;
import model.Usuario;

/**
 * Servlet implementation class MostrarLibrosDisponiblesUsuario
 */
@WebServlet("/MostrarLibrosUsuario")
public class CambiarPropietarioLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public CambiarPropietarioLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WallabookDAO wallabookDAO = new WallabookDAO();
		Libro libro = wallabookDAO.consultarLibroID(request.getParameter("idlibro"));
		String nickname = (String) request.getSession(false).getAttribute("me");
		Usuario antiguoPropietario =  wallabookDAO.consultarUsuarioNickname(nickname);
		Usuario nuevoPropietario = wallabookDAO.consultarUsuarioNickname(request.getParameter("idRemitente"));
		if (wallabookDAO.cambiarPropietarioLibro(libro, antiguoPropietario, nuevoPropietario)) {
			wallabookDAO.enviarNotificacionesCambioLibroOK(libro, antiguoPropietario, nuevoPropietario);
		}
		else {
			wallabookDAO.enviarNotificacionesCambioLibroError(libro, antiguoPropietario, nuevoPropietario);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
