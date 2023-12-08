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

@WebServlet(name = "ServletConvertPoints", value = "/ServletConvertPoints")
public class ServletConvertPoints extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        request.getRequestDispatcher("/WEB-INF/pageConvertPoints.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorMessage = "";
        request.setAttribute("errorMessage", errorMessage);
        String action = request.getParameter("action");
        Client client = Controller.getInstanceController().requestGetClient();
        if ("convertir".equals(action)) {
            // L'utilisateur a choisi de convertir les points en solde
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            if (quantite > client.getPoints()) {
                errorMessage = "Vous n'avez pas assez de points pour convertir " + quantite + " points.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/pageConvertPoints.jsp").forward(request, response);
            } else if (quantite < 1) {
                errorMessage = "Vous devez convertir au moins 1 point";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/pageConvertPoints.jsp").forward(request, response);
            } else { // Logique de conversion des points en solde (exemple : 1 points = 1 euro)
                BigDecimal montantSolde = BigDecimal.valueOf(quantite);
                BigDecimal soldeActuel = client.getCompteBancaireSolde();
                BigDecimal soldeApresModif = soldeActuel.add(montantSolde);
                client.setCompteBancaireSolde(soldeApresModif);

                int pointsApresModif = client.getPoints() - quantite;
                client.setPoints(pointsApresModif);

                Controller.getInstanceController().requestSetClient(client);
                UtilisateurDAO.updateClient(client);
                response.sendRedirect("ServletProfil");
                response.getWriter().println("Conversion réussie. Montant ajouté au solde : " + montantSolde);
            }

        } else if ("annuler".equals(action)) {
            // L'utilisateur a choisi de ne pas convertir les points
            response.sendRedirect("ServletProfil"); // Redirection vers la page de profil
        }
    }
}
