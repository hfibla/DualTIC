package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Categoria;

/**
 * Servlet implementation class AnadirLibroPrevio
 */
@WebServlet("/AnadirLibroPrevio")
public class AnadirLibroPrevio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       WallabookDAO wallabookDAO = new WallabookDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnadirLibroPrevio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Categoria> categorias = wallabookDAO.obtenerCategorias();
//		String nickname = request.getParameter("nickname");
//		String nickname = "priest";
//		request.setAttribute("me", nickname);
		request.setAttribute("categorias", categorias);
<<<<<<< HEAD
		request.getRequestDispatcher("/AnadirLibros/AnadirLibros.jsp").forward(request, response);
=======
		request.getRequestDispatcher("/AnadirLibros/AnadirLibros.jsp?usr=" + nickname).forward(request, response);
>>>>>>> 505c5f99b66787a06ee1d58bd3714d1a5070d34a
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
