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
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérez les données du formulaire
        String numCompte = request.getParameter("numeroCarte");
        Client client = Controller.getInstanceController().requestGetClient();
        if (client.getCompteBancaireNum().equals("0000 0000 0000 0000") || numCompte.isEmpty()) {
            String errorMessage = "Vous devez ajouter votre carte bleue sur votre profil avant de pouvoir ajouter de l'argent sur votre compte";
                    /*"l'argent sur votre compte" + "<form action=\"ServletAjouterMoyenDePaiement\" method=\"get\"> " +
                    "<button type=\"submit\">Ajouter Carte Bancaire</button></form>";*/
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
        } else if (!numCompte.equals(client.getCompteBancaireNum())) {
            String errorMessage = "Numéro de carte bleue incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
        } else {
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
        /* Il faut maintenant update la bdd avec nouveau solde et ajouter errorMessage dans pageAjouterSolde.jsp
        A faire plus tard
         */
    }
}
