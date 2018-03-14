package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
	
	//parece ser que no va a ser necesario
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
		PrintWriter out = response.getWriter();
		Libro libro = wallabookDAO.consultarLibroID(request.getParameter("idlibro"));
		Usuario antiguoPropietario = wallabookDAO.consultarUsuarioNickname(request.getParameter("olduser"));
		Usuario nuevoPropietario = wallabookDAO.consultarUsuarioNickname(request.getParameter("newuser"));
		String mensaje = wallabookDAO.cambiarPropietarioLibro(libro, antiguoPropietario, nuevoPropietario);
		out.println(mensaje);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
