package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Usuario;

/**
 * Servlet implementation class ObtenerPerfil
 */
@WebServlet("/ObtenerPerfil")
public class ObtenerPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObtenerPerfil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WallabookDAO wallabookDAO = new WallabookDAO();
		if (request.getParameterMap().containsKey("usr")) {
			String nickname = request.getParameter("usr");
			Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("/Perfil/perfil.jsp?usr=" + nickname).forward(request, response);
		} else {
			String nickname = (String) request.getSession(false).getAttribute("me");
			Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);			
			if (request.getParameterMap().containsKey("edit")) {
				String editStatus = request.getParameter("edit");
				if (editStatus.equalsIgnoreCase("pending")) {
					request.setAttribute("usuario", usuario);
					request.getRequestDispatcher("/perfilPropio/editarPerfil.jsp").forward(request, response);
				} 
//					else if (editStatus.equalsIgnoreCase("commit")) {
//					
//				}
			} else {
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher("/perfilPropio/miPerfil.jsp").forward(request, response);
			}
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			WallabookDAO wallabookDAO = new WallabookDAO();
			String nickname = (String) request.getSession(false).getAttribute("me");
			Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);	
			String nombreReal = "";
			String telefono = "";
//			if (!request.getParameter("nombreReal").equals(null)) {
				nombreReal = request.getParameter("nombreReal");
//			}
//			if (!request.getParameter("telefono").equals(null)) {
			telefono = request.getParameter("telefono");
//			}			
			String localidad = request.getParameter("localidad");
			Usuario usuarioEditado = wallabookDAO.actualizarDatosUsuario(usuario, nombreReal, telefono, localidad);
			if (usuarioEditado.equals(null)) {			
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("/perfilPropio/miPerfil.jsp?editok=0").forward(request, response);
			} else {
			request.setAttribute("usuario", usuarioEditado);
			request.getRequestDispatcher("/perfilPropio/miPerfil.jsp?editok=1").forward(request, response);
			}
	}
	
}
