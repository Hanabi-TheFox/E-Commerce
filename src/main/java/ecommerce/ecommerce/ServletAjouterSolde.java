package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ServletAjouterSolde", value = "/ServletAjouterSolde")
public class ServletAjouterSolde extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves form data
        String numCompte = request.getParameter("numeroCarte");
        Client client = Controller.getInstanceController().requestGetClient();
        if (client.getCompteBancaireNum().equals("0000 0000 0000 0000") || numCompte.isEmpty()) {
            /* if clients didn't add his credit card yet */
            String errorMessage = "Vous devez ajouter votre carte bleue sur votre profil avant de pouvoir ajouter de l'argent sur votre compte";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
        } else if (!numCompte.equals(client.getCompteBancaireNum())) {
            /* if the credit card value is incorrect */
            String errorMessage = "Numéro de carte bleue incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
        } else {
            /* if everything is good we add the money on his account */
            BigDecimal soldeAjoute = BigDecimal.valueOf(Integer.parseInt(request.getParameter("montant")));
            BigDecimal soldeActuel = client.getCompteBancaireSolde();
            if (soldeActuel == null) {
                soldeActuel = BigDecimal.ZERO;
            }
            BigDecimal soldeApresModif = soldeActuel.add(soldeAjoute);
            client.setCompteBancaireSolde(soldeApresModif);
            Controller.getInstanceController().requestSetClient(client);
            UtilisateurDAO.updateClient(client);
            response.sendRedirect("ServletProfil");
        }
    }
}
