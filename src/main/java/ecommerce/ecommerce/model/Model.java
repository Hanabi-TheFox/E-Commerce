package ecommerce.ecommerce.model;
import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;

import java.util.List;

public class Model {
    private Controller controller;
    private Utilisateur utilisateur;


    public Model(Controller controller) {
        this.controller = controller;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
