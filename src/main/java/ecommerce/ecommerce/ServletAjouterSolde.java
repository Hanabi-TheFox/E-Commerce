package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
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
        System.out.println("numero compte" + client.getCompteBancaireNum());
        if(client.getCompteBancaireNum().equals("0000 0000 0000 0000")){
            String errorMessage = "Vous devez d'abord ajouter votre carte bleue avant de pouvoir ajouter de " +
                    "l'argent sur votre compte <p><a href=\"ServletAjouterMoyenPaiement\">" +
                    "<button>Ajouter carte bancaire</button></a></p>";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
        }
        else if(!numCompte.equals(client.getCompteBancaireNum())){
            String errorMessage = "Numéro de carte bleue incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
        }
        String errorMessage = "";
        request.setAttribute("errorMessage", errorMessage);
        BigDecimal soldeAjoute = BigDecimal.valueOf(Integer.parseInt(request.getParameter("montant")));
        System.out.println(soldeAjoute);
        /* Il faut maintenant update la bdd avec nouveau solde et ajouter errorMessage dans pageAjouterSolde.jsp
        A faire plus tard
         */
    }
}
