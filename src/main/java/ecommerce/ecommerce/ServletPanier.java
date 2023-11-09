package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import entity.Commande;
import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
@WebServlet(name = "ServletPanier", value = "/ServletPanier")
public class ServletPanier extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        //Commande panier = new Commande();

        String action = request.getParameter("action");

        if (action.equals("ajouter")) {
            System.out.println("AJOUTER");
            //  les informations du produit à ajouter depuis le formulaire sont recuperées
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            String produitNom = request.getParameter("produitNom");
            float produitPrix = Float.parseFloat(request.getParameter("produitPrix"));
            int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
            // Ajoutez le produit au panier
            Produit produit = new Produit();
            produit.setIdProduit(produitId);
            produit.setNom(produitNom);
            produit.setStock(produitQuantite);

            Controller.getInstanceController().requestGetCommande().ajouterProduitDansPanier(produit);

            // Configurez l'attribut "montantTotal" dans la demande
            // Le reste de la logique pour calculer le montant total et afficher le panier
            // Redirigez ensuite vers la page JSP "panier.jsp" pour afficher le panier
            /*System.out.println("AFFICHAGE DE TOUS LES PRODUITS DANS PANIER");
            for (int i = 0;i< Controller.getInstanceController().requestGetPanier().size();i++) {
                System.out.println("PRODUIT : " + Controller.getInstanceController().requestGetPanier().get(i).getNom());
            }
            System.out.println(Controller.getInstanceController().requestGetPanier()); */

            request.setAttribute("panier", Controller.getInstanceController().requestGetPanier());
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
            System.out.println("pagePanier chargée");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //TODO

        //Il est recuperée si effacer apuyé:

        if( request.getParameter("action") != null) {
            Controller.getInstanceController().requestViderPanier();
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        }

    }
}
