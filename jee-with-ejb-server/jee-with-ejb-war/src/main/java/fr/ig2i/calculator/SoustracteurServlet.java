package fr.ig2i.calculator;

import fr.ig2i.calculator.CalculEntity.TypeOperation;
import fr.ig2i.calculator.exceptions.MissingInputException;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SoustracteurServlet
 */
@WebServlet(description = "Soustracteur de valeur", urlPatterns = { "/SoustracteurServlet" })
public class SoustracteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CalculService calculService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoustracteurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String pInteger1 = request.getParameter("pInteger1");
		String pInteger2 = request.getParameter("pInteger2");
		
		int resultat;

		try {
			resultat = calculService.calculOperation(TypeOperation.SOUSTRACTION, pInteger1, pInteger2);
		} catch (MissingInputException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Valeurs Manquantes");
			return;
		}

		buildSoustractionResponse(response, pInteger1, pInteger2, resultat);
	}

	private void buildSoustractionResponse(HttpServletResponse response, String pInteger1, String pInteger2,
			int resulat) throws IOException {

		PrintWriter pr = response.getWriter();
		response.setContentType("text/html");
		pr.println("<HTML>");
		pr.println("<HEAD><TITLE>TP Exercice 3</TITLE></HEAD>");
		pr.println("<BODY>");
		pr.println("<h1>Soustraction</h1>");
		pr.println("Premier Entier <input type=text value=" + pInteger1 + "><br><br>");
		pr.println("Second Entier <input type=text value=" + pInteger2 + "><br><br>");
		pr.println("Résultat <input type=text value=" + resulat + "><br><br>");
		pr.println("</BODY></HTML>");
	}

}
