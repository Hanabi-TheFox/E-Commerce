package ecommerce.ecommerce;

import entity.Utilisateur;
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
@WebServlet(name = "ServletProfil", value = "/ServletProfil")
public class ServletProfil extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        HttpSession session = request.getSession(false);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        /*String nom = utilisateur.getNom();
        String prenom = utilisateur.getPrenom();
        String email = utilisateur.getMail();
        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        request.setAttribute("email", email);
        request.getRequestDispatcher("/WEB-INF/pageProfil.jsp").forward(request, response); */
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement POST
    }
}