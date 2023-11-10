package ecommerce.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;

@WebServlet(name = "ServletModifierProfil", value = "/ServletModifierProfil")
public class ServletModifierProfil extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pageModifierProfil.jsp").forward(request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("mdp");

        // Vérifier si le mot de passe est correct
        Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
        if (u.getMotDePasse().equals(motDePasse)) {
            // Le mot de passe est correct, on modifie les données de l'utilisateur
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setMail(email);
            u.setMotDePasse(motDePasse);
            Controller.getInstanceController().requestSetUtilisateur(u);
            UtilisateurDAO.updateUtilisateur(u);
            response.sendRedirect("ServletProfil");
        } else {
            // Le mot de passe est incorrect, on affiche un message d'erreur
            String errorMessage = "Mot de passe incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageModifierProfil.jsp").forward(request, response);
        }
    }
}