package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Usuario;

/**
 * Servlet implementation class ConsultarLibrosServlet
 */
@WebServlet("/ConsultarLibrosUsuario")
public class ConsultarLibrosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;     
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarLibrosUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    	WallabookDAO wallabookDAO = new WallabookDAO();
		PrintWriter out = response.getWriter();
//		String nickname = request.getParameter("nickname");
		String nickname = "priest";
		Usuario usuario = wallabookDAO.consultarUsuarioNickname(nickname);
		if (usuario.equals(null)) {
		    out.println("Usuario no encontrado");
		}
		else {
		    request.setAttribute("usuario", usuario);
		    request.getRequestDispatcher("mostrarLibros.jsp/?usr=" + nickname).forward(request, response);
		}
	}

}
