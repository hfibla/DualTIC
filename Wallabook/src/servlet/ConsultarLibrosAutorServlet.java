package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WallabookDAO;
import model.Libro;

/**
 * Servlet implementation class ConsultarLibrosAutorServlet
 */
@WebServlet("/ConsultarLibrosAutor")
public class ConsultarLibrosAutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WallabookDAO wallabookDAO = new WallabookDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarLibrosAutorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String autor = request.getParameter("autor");
		List<Libro> libro = wallabookDAO.consultarLibrosAutor(autor);	
		out.println (libro);
		//rederigir a jsp ()
		// request.getRequestDispatcher("mostrarLibros.jsp").forward(request, response);
		
	}

}
