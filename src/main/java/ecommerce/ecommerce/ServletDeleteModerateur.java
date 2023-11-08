package ecommerce.ecommerce;

import java.io.IOException;
import java.util.List;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletDeleteModerateur", value = "/ServletDeleteModerateur")
public class ServletDeleteModerateur extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        request.getRequestDispatcher("/WEB-INF/supprimerModerateur.jsp").forward(request, response);
    }
}