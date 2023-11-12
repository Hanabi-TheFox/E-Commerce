package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletAjouterMoyenPaiement", value = "/ServletAjouterMoyenPaiement")
public class ServletAjouterMoyenPaiement extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        request.getRequestDispatcher("/WEB-INF/pageAjouterMoyenPaiement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String CB = request.getParameter("carteBancaire");

        Client c = Controller.getInstanceController().requestGetClient();
        c.setCompteBancaireNum(CB);
        Controller.getInstanceController().requestSetClient(c);
        UtilisateurDAO.updateClient(c);
        response.sendRedirect("ServletProfil");
    }
}