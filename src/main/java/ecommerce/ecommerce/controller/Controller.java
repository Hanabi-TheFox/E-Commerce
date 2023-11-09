package ecommerce.ecommerce.controller;
import ecommerce.ecommerce.model.Model;
import entity.*;

import java.util.List;

import java.util.List;

public class Controller {
    private Model model;
    private static Controller uniqueInstanceController = new Controller();

    private Controller () {
        //Instantiation du modele avec cette même classe controller
        this.model = new Model(this);
    }
    public static Controller getInstanceController() {
        return uniqueInstanceController;
    }

    public void requestSetUtilisateur(Utilisateur utilisateur) {
        this.model.setUtilisateur(utilisateur);
    }

    public Utilisateur requestGetUtilisateur(){
        return this.model.getUtilisateur();
    }

    public void requestViderUtilisateur(){
        this.model.viderUtilisateur();
    }

    public List<Utilisateur> requestGetListUtilisateurs(){return this.model.getListUtilisateurs();}

    public List<Produit> requestGetProduits() {
        return this.model.getListeProduits();
    }

    public Produit requestGetProduit(int id) {
        return this.model.getProduit(id);
    }

    public void requestSetModerateur(Moderateur moderateur) { this.model.setModerateur(moderateur);
    }

    public Moderateur requestGetModerateur() { return this.model.getModerateur();
    }

    public List<Produit> requestGetPanier() {
        return this.model.getPanier();
    }

    public void requestCreateCommande(int idClient){
        this.model.createCommande(idClient);
    }

    public void requestViderPanier(){
        this.model.viderPanier();
    }

    public Commande requestGetCommande(){
        return this.model.getCommande();
    }

    public Client requestGetClient(){
        return this.model.getClient();
    }

    public void requestSetClient(Client c){
        this.model.setClient(c);
    }
}
