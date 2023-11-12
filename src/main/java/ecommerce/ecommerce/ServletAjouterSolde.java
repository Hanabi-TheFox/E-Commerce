package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import entity.Client;
import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;

public class ServletAjouterSolde {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérez les données du formulaire
        String numCompte = request.getParameter("numeroCarte");
        Client client = Controller.getInstanceController().requestGetClient();
        if(!numCompte.equals(client.getCompteBancaireNum())){
            String errorMessage = "Numéro de carte bleu incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageAjouterSolde.jsp").forward(request, response);
        }
        BigDecimal soldeAjoute = BigDecimal.valueOf(Integer.parseInt(request.getParameter("montantSolde")));
        System.out.println(soldeAjoute);
        /* Il faut maintenant update la bdd avec nouveau solde et ajouter errorMessage dans pageAjouterSolde.jsp
        A faire plus tard
         */
    }
}
