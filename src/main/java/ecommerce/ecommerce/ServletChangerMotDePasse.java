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

@WebServlet(name = "ServletChangerMotDePasse", value = "/ServletChangerMotDePasse")
public class ServletChangerMotDePasse extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pageChangerMotDePasse.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves form data
        String motDePasse = request.getParameter("oldPassword");
        String nouveauMotDePasse = request.getParameter("newPassword");
        String confirmationMotDePasse = request.getParameter("confirmPassword");

        // Checks if the password is correct
        Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
        if (u.getMotDePasse().equals(motDePasse) && nouveauMotDePasse.equals(confirmationMotDePasse)) {
            //if password is correct we update the password
            u.setMotDePasse(nouveauMotDePasse);
            Controller.getInstanceController().requestSetUtilisateur(u);
            UtilisateurDAO.updateUtilisateur(u);
            response.sendRedirect("ServletProfil");
        } else {
            // if password is incorrect we show a message
            String errorMessage = "Mot de passe incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageChangerMotDePasse.jsp").forward(request, response);
        }
    }
}