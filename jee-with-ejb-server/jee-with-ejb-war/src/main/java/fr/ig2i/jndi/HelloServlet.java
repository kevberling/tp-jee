package fr.ig2i.jndi;

import jakarta.annotation.Resource;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Resource(lookup = "java:global/hello")
	private String hello;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append(hello);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pr = response.getWriter();
		response.setContentType("text/html");
			pr.println("<HTML>");
			pr.println("<HEAD><TITLE>Accueil</TITLE></HEAD>");
			pr.println("<BODY>");
			pr.println("<h1>" + hello + "</h1>");
			pr.println("</BODY></HTML>");
	}

}
