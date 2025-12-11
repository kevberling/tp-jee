package fr.ig2i.calculator;

import fr.ig2i.calculator.CalculEntity.TypeOperation;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class AdditionneurServlet
 */
@WebServlet(description = "Un additionneur de deux entiers", urlPatterns = {"/AdditionneurServlet"},
		initParams = {@WebInitParam(name = "entier1", value = "0", description = "Premier entier par défaut"),
				@WebInitParam(name = "entier2", value = "0", description = "Second entier par défaut")})
public class AdditionneurServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<CalculEntity> calculs;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdditionneurServlet() {
        super();
        calculs = new ArrayList<>();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (calculs.isEmpty()) {
            response.sendError(0, "Aucun calcul effectué");
            response.getWriter().append("Aucun calcul effectué");
        } else {
            CalculEntity lastCalcul = calculs.get(calculs.size() - 1);
            buildAdditionResponse(response, lastCalcul.getValue1(), lastCalcul.getValue2());
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
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

        calculs.add(new CalculEntity(TypeOperation.ADDITION, pInteger1, pInteger2));
        PrintWriter pr = response.getWriter();
        response.setContentType("text/html");
        try {
            int int1 = Integer.parseInt(pInteger1);
            int int2 = Integer.parseInt(pInteger2);
            pr.println("<HTML>");
            pr.println("<HEAD><TITLE>TP Exercice 2</TITLE></HEAD>");
            pr.println("<BODY>");
            pr.println("<h1>Addition</h1>");
            pr.println("Premier Entier <input type=text value=" + pInteger1 + "><br><br>");
            pr.println("Second Entier <input type=text value=" + pInteger2 + "><br><br>");
            pr.println("Résultat <input type=text value=" + CalculUtils.addition(int1, int2) + "><br><br>");
            pr.println("</BODY></HTML>");
        } catch (Exception e) {
            pr.println("Invalid Input");
        }
    }

}
