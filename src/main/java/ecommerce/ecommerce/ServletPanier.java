package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.CommandeDAO;
import ecommerce.ecommerce.model.DAO.CommandeProduitDAO;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import entity.Commande;
import entity.CommandeProduit;
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
                    boolean produitExisteDeja = false;
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) { // le produit existe deja dans le panier
                            ajouterPrix(p);
                            p.setStock(p.getStock() + produitQuantite);
                            Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            produitExisteDeja = true;
                            break;
                        }
                    }
                    if(!produitExisteDeja){
                        Produit produit = ProduitDAO.getProduitById(produitId);
                        if (produit != null) {
                            produit.setStock(produitQuantite);
                            ajouterPrix(produit);
                        }
                        Controller.getInstanceController().requestGetCommande().ajouterProduitDansPanier(produit);
                    }
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
                            break;
                        }
                    }
                    break;
                case "modifier": {
                    int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) {
                            supprimerPrix(p);
                            p.setStock(produitQuantite);
                            ajouterPrix(p);
                            Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                            break;
                        }
                    }
                    break;
                }
            }
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("vider")){
            Controller.getInstanceController().requestViderPanier();
            Controller.getInstanceController().requestGetCommande().setPrix(0);
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        }else if(action.equals("payer")){
            /*Commande commande = Controller.getInstanceController().requestGetCommande();
            List<Produit> panier = commande.getPanier();
            Commande commandeBDD = new Commande();
            commandeBDD.setIdClient(commande.getIdClient());
            commandeBDD.setPrix(commande.getPrix());
            commandeBDD.setStatus("payé");
            CommandeDAO.addCommande(commandeBDD);
            // on recupere l'id de la commande qu'on vient d'ajouter
            int idCommandeBDD = CommandeDAO.getIdFromLastCommande();
            System.out.println("ID commande ajouté = " + idCommandeBDD);
            CommandeProduit panierBDD = new CommandeProduit();
            for (Produit produit : panier){
                panierBDD.setIdCommande(idCommandeBDD);
                panierBDD.setIdProduit(produit.getIdProduit());
                panierBDD.setQuantite(produit.getStock());
                CommandeProduitDAO.addCommandeProduit(panierBDD);
            }
            Controller.getInstanceController().requestCreateCommande(commande.getIdClient());*/
            request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
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
