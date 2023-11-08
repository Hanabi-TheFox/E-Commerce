package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Client;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletDeDesinscription", value = "/ServletDeDesinscription")
public class ServletDeDesinscription extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur = Controller.getInstanceController().requestGetUtilisateur();
        // On supprime l'utilisateur de la base de données
        UtilisateurDAO.removeUtilisateur(utilisateur.getMail());
        // On vide l'utilisateur de la session
        Controller.getInstanceController().requestViderUtilisateur();
        // On redirige vers la page d'accueil
        response.sendRedirect("ServletDeConnexion");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}