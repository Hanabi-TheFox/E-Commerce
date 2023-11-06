package ecommerce.ecommerce.model;
import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import entity.Produit;
import entity.Utilisateur;

import java.util.List;

public class Model {
    private Controller controller;
    private Utilisateur utilisateur;
    private Produit produit;


    public Model(Controller controller) {
        this.controller = controller;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Produit> getListeProduits(){
        return ProduitDAO.getListProduits();
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void viderUtilisateur(){
        this.utilisateur = null;
    }

    public List<Utilisateur> getListUtilisateurs(){
        return UtilisateurDAO.getListUtilisateurs();
    }
}
