package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletModifierProfil", value = "/ServletModifierProfil")
public class ServletModifierProfil extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pageModifierProfil.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves form data
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("mdp");

        // Vérifies if user password is ok
        Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
        if (u.getMotDePasse().equals(motDePasse)) {
            // We update user info
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setMail(email);
            u.setMotDePasse(motDePasse);
            Controller.getInstanceController().requestSetUtilisateur(u);
            UtilisateurDAO.updateUtilisateur(u);
            response.sendRedirect("ServletProfil");
        } else {
            // Incorrect password, we show the error message
            String errorMessage = "Mot de passe incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageModifierProfil.jsp").forward(request, response);
        }
    }
}