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
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;

import javax.sound.midi.ControllerEventListener;

@WebServlet(name = "ServletPanier", value = "/ServletPanier")
public class ServletPanier extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        //Commande panier = new Commande();

        String action = request.getParameter("action");
        if (action == null) { // action is null if we click on header button 'Panier'
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        } else {
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            List<Produit> panier = Controller.getInstanceController().requestGetPanier();
            switch (action) {
                case "ajouter": {
                    int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) { // le produit existe deja dans le panier
                            p.setStock(p.getStock() + produitQuantite);
                            ajouterPrix(p);
                            Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                        }
                    }
                    Produit produit = ProduitDAO.getProduitById(produitId);
                    if (produit != null) {
                        produit.setStock(produitQuantite);
                        ajouterPrix(produit);
                    }
                    Controller.getInstanceController().requestGetCommande().ajouterProduitDansPanier(produit);
                    request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                    break;
                }
                case "supprimer":
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) {
                            panier.remove(p);
                            Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            supprimerPrix(p);
                            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                        }
                    }
                    break;
                case "modifier": {
                    int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) {
                            p.setStock(produitQuantite);
                            supprimerPrix(p);
                            ajouterPrix(p);
                            Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                        }
                    }
                    break;
                }
            }
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //TODO

        //Il est recuperée si effacer apuyé:

        if( request.getParameter("action").equals("vider")) {
            Controller.getInstanceController().requestViderPanier();
            Controller.getInstanceController().requestGetCommande().setPrix(0);
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        }
    }

    private void ajouterPrix(Produit p){
        float prix = Controller.getInstanceController().requestGetCommande().getPrix();
        Controller.getInstanceController().requestGetCommande().setPrix(prix + (p.getPrix() * p.getStock()));
    }
    private void supprimerPrix(Produit p){
        float prix = Controller.getInstanceController().requestGetCommande().getPrix();
        Controller.getInstanceController().requestGetCommande().setPrix(prix - (p.getPrix() * p.getStock()));
    }
}
