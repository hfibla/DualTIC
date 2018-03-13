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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    WallabookDAO wallabookDAO = new WallabookDAO();
	    if (request.getParameterMap().containsKey("usr")) {
	    	String nickname = request.getParameter("usr");
	 	    Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);	    
	 	    request.setAttribute("usuario", usuario);
	 	    request.getRequestDispatcher("/Perfil/perfil.jsp?usr=" + nickname).forward(request, response);
	    } else {
	    	String nickname = (String) request.getSession(false).getAttribute("me");
			Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("/perfilPropio/miPerfil.jsp").forward(request, response);
	    	}
		}

	}


