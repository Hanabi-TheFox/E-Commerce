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

@WebServlet(name = "ServletProduits", value = "/ServletProduits")
public class ServletProduits extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produit> listeProduits = Controller.getInstanceController().requestGetProduits();
        request.setAttribute("listeProduits", listeProduits);
        request.setAttribute("client", Controller.getInstanceController().requestGetClient());
        request.getRequestDispatcher("/WEB-INF/pageProduits.jsp").forward(request, response);
    }

}