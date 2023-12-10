package ecommerce.ecommerce;

import ecommerce.ecommerce.model.DAO.ProduitDAO;
import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletAjouterProduit", value = "/ServletAjouterProduit")
@MultipartConfig
public class ServletAjouterProduit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", "");
        request.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        if (existeProduit(nom)) {
            // If it exists, prints a message
            String errorMessage = "Un produit avec ce nom existe déjà, veuillez choisir un autre nom";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
        } else {
            String description = request.getParameter("description");
            Float prix = Float.parseFloat(request.getParameter("prix"));

            Integer stock = Integer.parseInt(request.getParameter("stock"));

            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setPrix(prix);
            produit.setDescription(description);
            produit.setStock(stock);

            ProduitDAO.addProduit(produit);

            //We assign the productId to the imageId
            int idImage = ProduitDAO.findIdProductdByProduct(produit);

            /* Get the path of where the image is going to be stocked */
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
                // If already exists a product with the same name
                return true;
            }
        }

        return false;
    }


}
