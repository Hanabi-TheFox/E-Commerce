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

@WebServlet(name = "ServletAjouterProduit", value = "/ServletAjouterProduit")
@MultipartConfig
public class ServletAjouterProduit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérez les données du formulaire
        String nom = request.getParameter("nom");
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
        String imageFileName = idImage+".jpeg";
        System.out.println("nomimage :" + imageFileName);
        String imageDirectory = "/imagesProduct/";
        String imageFilePath = uploadPath + imageFileName;

        produit.setImagePath(imageFilePath);
        System.out.println("nomImagePath :" + produit.getImagePath());
        imagePart.write(imageFilePath);
        System.out.println("final");
        // Redirigez l'utilisateur vers une page de confirmation ou une autre page appropriée
        response.sendRedirect("ServletProduits");
    }


}
