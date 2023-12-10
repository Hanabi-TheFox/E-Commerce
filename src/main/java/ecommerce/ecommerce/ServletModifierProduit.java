package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
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

@WebServlet(name = "ServletModifierProduit", value = "/ServletModifierProduit")
@MultipartConfig
public class ServletModifierProduit extends HttpServlet {
    private Produit produit;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", "");
        //Retrieves product id
        Produit produit = Controller.getInstanceController().requestGetProduit(Integer.parseInt(request.getParameter("idProduit")));
        this.produit = produit;
        request.setAttribute("nom", produit.getNom());
        request.setAttribute("description", produit.getDescription());
        request.setAttribute("prix", produit.getPrix());
        request.setAttribute("stock", produit.getStock());
        request.getRequestDispatcher("/WEB-INF/pageModifierProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nouveauNom = request.getParameter("nom");
        String nom = this.produit.getNom();

        if (nouveauNom.equals(nom) || !existeProduit(nouveauNom)) {
            String description = request.getParameter("description");
            Float prix = Float.parseFloat(request.getParameter("prix"));

            Integer stock = Integer.parseInt(request.getParameter("stock"));

            // We update the product
            this.produit.setNom(nouveauNom);
            this.produit.setPrix(prix);
            this.produit.setDescription(description);
            this.produit.setStock(stock);
            ProduitDAO.updateProduit(this.produit);

            //We update the image
            if (request.getPart("image") != null && request.getPart("image").getSize() > 0) {
                String uploadPath = getServletContext().getRealPath("/") + "imagesProduct/";
                Part imagePart = request.getPart("image");
                String imageFileName = this.produit.getIdProduit() + ".jpeg";
                String imageFilePath = uploadPath + imageFileName;

                produit.setImagePath(imageFilePath);
                imagePart.write(imageFilePath);
                response.sendRedirect("ServletProduits");
            } else {
                response.sendRedirect("ServletProduits");
            }
        } else if (existeProduit(nom)) {
            // User exists, show an errorMessage
            String errorMessage = "Un produit avec ce nom existe déjà, veuillez choisir un autre nom";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
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