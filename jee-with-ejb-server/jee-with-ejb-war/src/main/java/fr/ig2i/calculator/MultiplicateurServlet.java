package fr.ig2i.calculator;

import fr.ig2i.ejb.ProductLocalEJB;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class MultiplicateurServlet
 */
@WebServlet("/MultiplicateurServlet")
public class MultiplicateurServlet extends HttpServlet {
	
	@EJB
	private ProductLocalEJB productLocalEJB;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiplicateurServlet() {
        super();
        // Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pInteger1 = request.getParameter("pInteger1");
		String pInteger2 = request.getParameter("pInteger2");

		if (pInteger1 == null || pInteger1.isEmpty() || pInteger2 == null || pInteger2.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Valeurs Manquantes");
		} else {
			buildAdditionResponse(response, pInteger1, pInteger2);
		}
	}
	
	private void buildAdditionResponse(HttpServletResponse response, String pInteger1, String pInteger2) throws IOException {
		
		PrintWriter pr = response.getWriter();
		response.setContentType("text/html");
		try {
			int int1 = Integer.parseInt(pInteger1);
			int int2 = Integer.parseInt(pInteger2);
			pr.println("<HTML>");
			pr.println("<HEAD><TITLE>TP Exercice 6</TITLE></HEAD>");
			pr.println("<BODY>");
			pr.println("<h1>Multiplication</h1>");
			pr.println("Premier Entier <input type=text value=" + pInteger1 + "><br><br>");
			pr.println("Second Entier <input type=text value=" + pInteger2 + "><br><br>");
			pr.println("Résultat <input type=text value=" + productLocalEJB.productMethod(int1, int2) + "><br><br>");
			pr.println("</BODY></HTML>");
		} catch (Exception e) {
			pr.println("Invalid Input");
		}
	}


}
