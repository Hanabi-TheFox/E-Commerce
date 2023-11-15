package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.CommandeDAO;
import ecommerce.ecommerce.model.DAO.CommandeProduitDAO;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ServletPayerCommande", value = "/ServletPayerCommande")
public class ServletPayerCommande extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carte = request.getParameter("numeroCarte");
        String errorMessage="";
        Client client = Controller.getInstanceController().requestGetClient();
        if(client.getCompteBancaireNum().equals("0000 0000 0000 000")){ // si le client n'a pas de CB
            errorMessage = "Vous devez d'abord ajouter une carte bancaire ";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
        }
        else if(carte.equals(client.getCompteBancaireNum())){
            Commande commande = Controller.getInstanceController().requestGetCommande();
            List<Produit> panier = commande.getPanier();
            if(client.getCompteBancaireSolde().floatValue() >= commande.getPrix()){
                List<Produit> listeProduits = Controller.getInstanceController().requestGetProduits();
                acceptPayment(commande, panier, listeProduits);
                request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
            }else {
                errorMessage = "Solde trop faible (solde actuel = " + client.getCompteBancaireSolde() + ")";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
            }
        }else {
            errorMessage = "Numéro de carte incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
        }
    }


    private void acceptPayment(Commande commande, List<Produit> panier, List<Produit> prods){
        // Manque plus que gérer les points de fidélités et soustraire le prix de la commande au solde actuel du client

        // create Commande entity in our bdd
        Commande commandeBDD = new Commande();
        commandeBDD.setIdClient(commande.getIdClient());
        commandeBDD.setPrix(commande.getPrix());
        commandeBDD.setStatus("payé");
        CommandeDAO.addCommande(commandeBDD);
        // we need the id of the last 'Commande' entity in order to create a 'CommandeProduit' entity
        int idCommandeBDD = CommandeDAO.getIdFromLastCommande();
        CommandeProduit panierBDD = new CommandeProduit();
        for (Produit produit : panier){
            // change stock
            for(Produit p : prods){
                if (p.getIdProduit() == produit.getIdProduit()){
                    p.setStock(p.getStock() - produit.getStock());
                    ProduitDAO.updateProduct(p);
                    break;
                }
            }

            // add CommandeProduit entity in our bdd
            panierBDD.setIdCommande(idCommandeBDD);
            panierBDD.setIdProduit(produit.getIdProduit());
            panierBDD.setQuantite(produit.getStock());
            CommandeProduitDAO.addCommandeProduit(panierBDD);
        }
        Controller.getInstanceController().requestCreateCommande(commande.getIdClient());
        // maj du solde client
        Utilisateur user = Controller.getInstanceController().requestGetUtilisateur();
        Client client = UtilisateurDAO.findClientByUtilisateur(user);
        assert client != null;
        float newSolde = client.getCompteBancaireSolde().floatValue() - commande.getPrix();
        client.setCompteBancaireSolde(new BigDecimal(newSolde));

        // Point de fidélité
        int pointsFidelite = (int) (Math.floor(commande.getPrix()) / 10);
        client.setPoints(client.getPoints()+pointsFidelite);

        UtilisateurDAO.updateClient(client);
    }

}