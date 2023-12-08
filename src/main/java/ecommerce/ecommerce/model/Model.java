package ecommerce.ecommerce.model;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.*;

import java.util.List;

public class Model {
    private Controller controller;
    private Utilisateur utilisateur;
    private Produit produit;

    private Moderateur moderateur;

    private Commande commande;

    private Client client;

    public Model(Controller controller) {
        this.controller = controller;
    }

    public List<Produit> getListeProduits() {
        return ProduitDAO.getListProduits();
    }

    public Produit getProduit(int id) {
        return ProduitDAO.getProduitById(id);
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void viderUtilisateur() {
        this.utilisateur = null;
    }

    public List<Utilisateur> getListUtilisateurs() {
        return UtilisateurDAO.getListUtilisateurs();
    }

    public Moderateur getModerateur() {
        return moderateur;
    }

    public void setModerateur(Moderateur moderateur) {
        this.moderateur = moderateur;
    }

    public List<Produit> getPanier() {
        return this.commande.getPanier();
    }

    public Commande getCommande() {
        return this.commande;
    }

    public void createCommande(int idClient) {
        this.commande = new Commande();
        this.commande.setIdClient(idClient);
        this.commande.setPrix(0);
        this.commande.setStatus("non payé");
    }

    public void viderPanier() {
        this.getPanier().clear();
    }

    ///CLIENT//

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = new Client();
        this.client.setIdClient(client.getIdClient());
        this.client.setPoints(client.getPoints());
        this.client.setCompteBancaireNum(client.getCompteBancaireNum());
        this.client.setCompteBancaireSolde(client.getCompteBancaireSolde());
    }


    ///////////
}
