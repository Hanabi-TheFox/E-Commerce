package ecommerce.ecommerce.controller;
import ecommerce.ecommerce.model.Model;
import entity.Produit;
import entity.Utilisateur;
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


}
