package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletModificationProduit", value = "/ServletModificationProduit")
public class ServletModificationProduit extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProduit = Integer.parseInt(request.getParameter("idProduit"));
        //We retrieve product informations
        Produit produit = Controller.getInstanceController().requestGetProduit(idProduit);
        request.setAttribute("nom", produit.getNom());
        request.setAttribute("description", produit.getDescription());
        request.setAttribute("prix", produit.getPrix());
        request.setAttribute("stock", produit.getStock());
        request.getRequestDispatcher("/WEB-INF/pageModifierProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (existeProduit(request.getParameter("nom"))) {
            //If already exists a product with the same name, error
            String errorMessage = "Un produit avec ce nom existe déjà, veuillez choisir un autre nom";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageModifierProduit.jsp").forward(request, response);
        } else {
            //We convert the following parameters
            Float prix = Float.parseFloat(request.getParameter("prix"));
            Integer stock = Integer.parseInt(request.getParameter("stock"));

            Produit produit = new Produit();
            produit.setNom(request.getParameter("nom"));
            produit.setPrix(prix);
            produit.setDescription(request.getParameter("description"));
            produit.setStock(stock);

            ProduitDAO.addProduit(produit);

            int idImage = ProduitDAO.findIdProductdByProduct(produit);

            String uploadPath = getServletContext().getRealPath("/") + "imagesProduct/";
            Part imagePart = request.getPart("image");
            String imageFileName = idImage + ".jpeg";
            String imageFilePath = uploadPath + imageFileName;

            produit.setImagePath(imageFilePath);
            imagePart.write(imageFilePath);
            response.sendRedirect("ServletProduits");
        }
    }

    private boolean existeProduit(String nomProduit) {
        List<Produit> listeProduits = ProduitDAO.getListProduits();
        for (Produit produit : listeProduits) {
            if (produit.getNom().equals(nomProduit)) {
                return true;
            }
        }

        return false;
    }
}
