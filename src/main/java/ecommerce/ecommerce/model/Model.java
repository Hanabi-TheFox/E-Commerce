package ecommerce.ecommerce.model;
import ecommerce.ecommerce.controller.Controller;
import entity.Utilisateur;

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
}
