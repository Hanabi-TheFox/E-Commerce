package ecommerce.ecommerce.controller;
import ecommerce.ecommerce.model.Model;
import entity.Utilisateur;

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


}
