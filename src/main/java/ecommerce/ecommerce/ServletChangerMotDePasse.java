package ecommerce.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;

@WebServlet(name = "ServletChangerMotDePasse", value = "/ServletChangerMotDePasse")
public class ServletChangerMotDePasse extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pageChangerMotDePasse.jsp").forward(request, response);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String motDePasse = request.getParameter("oldPassword");
        String nouveauMotDePasse = request.getParameter("newPassword");
        String confirmationMotDePasse = request.getParameter("confirmPassword");
    
        // Vérifier si le mot de passe est correct
        Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
        if(u.getMotDePasse().equals(motDePasse) && nouveauMotDePasse.equals(confirmationMotDePasse)) {
            // Le mot de passe est correct, on modifie les données de l'utilisateur
            u.setMotDePasse(nouveauMotDePasse);
            Controller.getInstanceController().requestSetUtilisateur(u);
            UtilisateurDAO.updateUtilisateur(u);
            response.sendRedirect("ServletProfil");
        } else {
            // Le mot de passe est incorrect, on affiche un message d'erreur
            String errorMessage = "Mot de passe incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageChangerMotDePasse.jsp").forward(request, response);
        }
    }
}