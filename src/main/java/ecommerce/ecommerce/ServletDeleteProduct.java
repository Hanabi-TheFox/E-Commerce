package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletDeleteProduct", value = "/ServletDeleteProduct")
public class ServletDeleteProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        int idProduit = Integer.parseInt(request.getParameter("idProduit"));
        ProduitDAO.removeProduit(idProduit);
        //Il est recuperée l'id du produit via l'url
        request.getRequestDispatcher("/WEB-INF/pageProfil.jsp").forward(request, response);
    }

   /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        //Il est recuperée l'id du produit via l'url
        request.getRequestDispatcher("/WEB-INF/pageProduits.jsp").forward(request, response);
    }*/
}
