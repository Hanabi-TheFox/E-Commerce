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
        Client client = Controller.getInstanceController().requestGetClient();
        if(client.getCompteBancaireNum().equals("0000 0000 0000 000")){ // si le client n'a pas de CB
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            System.out.println("Le client doit ajouter une carte bleue");
            response.sendRedirect("ServletProduits");
        }
        else if(carte.equals(client.getCompteBancaireNum())){
            Commande commande = Controller.getInstanceController().requestGetCommande();
            List<Produit> panier = commande.getPanier();
            if(client.getCompteBancaireSolde().floatValue() >= commande.getPrix()){
                List<Produit> listeProduits = Controller.getInstanceController().requestGetProduits();
                for (Produit panierProd : panier){
                    for(Produit p : listeProduits){
                        if ( panierProd.getIdProduit() == p.getIdProduit() && panierProd.getStock() > p.getStock()){
                            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                            System.out.println("Pas assez de stock pour le produit '" + p.getNom() + "', stock actuel : " + p.getStock() + "< " + panierProd.getStock() + "(stock demande)");
                            response.sendRedirect("ServletProduits");
                        }
                    }
                }
                acceptPayment(commande, panier, listeProduits);
                response.sendRedirect("ServletProduits");
            }else {
                // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
                System.out.println("Solde trop faible (solde actuel = " + client.getCompteBancaireSolde() + ") => retour à l'accueil");
                response.sendRedirect("ServletProduits");
            }
        }else { // numero de carte invalide
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            // /!\ A retravailler : mise ne place d'un message d'erreur + rester sur le form si possible /!\
            System.out.println("Numero de carte invalide : carte != " + client.getCompteBancaireNum() + " => retour à l'accueil");
            response.sendRedirect("ServletProduits");
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

        int pointsFidelite = (int) (Math.floor(commande.getPrix()) / 10);
        System.out.println(commande.getPrix());
        System.out.println(pointsFidelite);
        client.setPoints(client.getPoints()+pointsFidelite);

        UtilisateurDAO.updateClient(client);
    }

}