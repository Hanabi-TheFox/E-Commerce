package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletDeDeconnexion", value = "/ServletDeDeconnexion")
public class ServletDeDeconnexion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        Controller.getInstanceController().requestViderUtilisateur();
        response.sendRedirect("ServletDeConnexion");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode POST
    }
}