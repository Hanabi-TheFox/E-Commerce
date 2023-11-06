package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
import entity.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.IOException;
import java.util.List;
@WebServlet(name = "ServletProfil", value = "/ServletProfil")
public class ServletProfil extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        Utilisateur utilisateur = Controller.getInstanceController().requestGetUtilisateur();
        Object typeDeCompte = utilisateur.getTypeDeCompte();
        if (typeDeCompte.equals("Client")){
            request.setAttribute("nom", utilisateur.getNom());
            request.setAttribute("prenom", utilisateur.getPrenom());
            request.setAttribute("email", utilisateur.getMail());
            Client client = UtilisateurDAO.findClientByUtilisateur(utilisateur);
            request.setAttribute("solde", client.getCompteBancaireSolde());
            request.setAttribute("points", client.getPoints());

            request.getRequestDispatcher("/WEB-INF/pageProfil.jsp").forward(request, response);
        }else if (typeDeCompte.equals("Admin")){
            request.setAttribute("nom", utilisateur.getNom());
            request.setAttribute("prenom", utilisateur.getPrenom());
            request.setAttribute("email", utilisateur.getMail());
            request.getRequestDispatcher("/WEB-INF/pageProfilAdmin.jsp").forward(request, response);
        }else { // typeDeCompte == "Moderateur"
            request.setAttribute("nom", utilisateur.getNom());
            request.setAttribute("prenom", utilisateur.getPrenom());
            request.setAttribute("email", utilisateur.getMail());
            request.getRequestDispatcher("/WEB-INF/pageProfil.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement POST
    }
}