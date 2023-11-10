package ecommerce.ecommerce;

import ecommerce.ecommerce.model.DAO.ProduitDAO;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Produit;
import entity.Utilisateur;
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
        // Récupérez les données du formulaire
        String nom = request.getParameter("nom");
        if(existeProduit(nom)){
                // L'utilisateur existe, afficher un message d'erreur
                String errorMessage = "Un produit avec ce nom existe déjà, veuillez choisir un autre nom";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
        }else {
            String description = request.getParameter("description");
            Float prix = Float.parseFloat(request.getParameter("prix"));
            // Convertissez le stock en entier
            Integer stock = Integer.parseInt(request.getParameter("stock"));


            // Maintenant, vous pouvez créer un objet Produit avec les données soumises
            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setPrix(prix);
            produit.setDescription(description);
            produit.setStock(stock);

            // Ajoutez le produit à votre base de données ou à votre liste de produits
            // Exemple (à adapter à votre DAO ou modèle de données) :
            ProduitDAO.addProduit(produit);

            int idImage = ProduitDAO.findIdProductdByProduct(produit);

            String uploadPath = getServletContext().getRealPath("/") + "imagesProduct/";
            Part imagePart = request.getPart("image");
            String imageFileName = idImage + ".jpeg";
            String imageFilePath = uploadPath + imageFileName;

            produit.setImagePath(imageFilePath);
            imagePart.write(imageFilePath);
            // Redirigez l'utilisateur vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("ServletProduits");
        }
    }

    private boolean existeProduit(String nomProduit){
            List<Produit> listeProduits = ProduitDAO.getListProduits();
            for (Produit produit : listeProduits) {
                if (produit.getNom().equals(nomProduit)) {
                    // L'utilisateur existe et le mot de passe est correct
                    return true;
                }
            }

            return false;
    }


}
