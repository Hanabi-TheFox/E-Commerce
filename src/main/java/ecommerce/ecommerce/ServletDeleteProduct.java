package ecommerce.ecommerce;

import ecommerce.ecommerce.model.DAO.ProduitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletDeleteProduct", value = "/ServletDeleteProduct")
public class ServletDeleteProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduit = Integer.parseInt(request.getParameter("idProduit"));
        // We retrieve productId from URL*/
        ProduitDAO.removeProduit(idProduit);
        Boolean suppression = true;
        request.setAttribute("suppression", suppression);
        response.sendRedirect("ServletProduits");
    }
}
