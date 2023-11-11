package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
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
        if (action == null) { // action is null if we click on header button 'Panier'
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        } else if (action.equals("ajouter")) {
            List<Produit> panier = Controller.getInstanceController().requestGetPanier();
            //  les informations du produit à ajouter depuis le formulaire sont recuperées
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
            for (Produit p : panier){
                if (p.getIdProduit() == produitId){ // le produit existe deja dans le panier
                    p.setStock(p.getStock() + produitQuantite);
                    Controller.getInstanceController().requestGetCommande().setPanier(panier);
                    request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                }
            }
            Produit produit = ProduitDAO.getProduitById(produitId);
            if (produit != null) {
                produit.setStock(produitQuantite);
            }
            Controller.getInstanceController().requestGetCommande().ajouterProduitDansPanier(produit);
            // Configurez l'attribut "montantTotal" dans la demande
            // Le reste de la logique pour calculer le montant total et afficher le panier
            // Redirigez ensuite vers la page JSP "panier.jsp" pour afficher le panier
        /*System.out.println("AFFICHAGE DE TOUS LES PRODUITS DANS PANIER");
        for (int i = 0;i< Controller.getInstanceController().requestGetPanier().size();i++) {
            System.out.println("PRODUIT : " + Controller.getInstanceController().requestGetPanier().get(i).getNom());
        }
        System.out.println(Controller.getInstanceController().requestGetPanier()); */

            /*request.setAttribute("panier", Controller.getInstanceController().requestGetPanier());*/
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
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
