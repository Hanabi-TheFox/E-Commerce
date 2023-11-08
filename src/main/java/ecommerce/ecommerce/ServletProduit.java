package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletProduit", value = "/ServletProduit")
public class ServletProduit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        //Il est recuperée l'id du produit via l'url
        String idProduit = request.getParameter("id");
        Produit produit = Controller.getInstanceController().requestGetProduit(Integer.parseInt(idProduit));
        request.setAttribute("produit", produit);
        request.getRequestDispatcher("/WEB-INF/pageProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode POST
    }
}