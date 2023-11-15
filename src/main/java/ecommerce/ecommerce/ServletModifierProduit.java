package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
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

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "ServletModifierProduit", value = "/ServletModifierProduit")
@MultipartConfig
public class ServletModifierProduit extends HttpServlet {
    private Produit produit;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMessage", "");
        //recuperation de l'id du produit
        Produit produit = Controller.getInstanceController().requestGetProduit(Integer.parseInt(request.getParameter("idProduit")));
        this.produit = produit;
        request.setAttribute("nom",produit.getNom());
        request.setAttribute("description",produit.getDescription());
        request.setAttribute("prix",produit.getPrix());
        request.setAttribute("stock",produit.getStock());
        request.getRequestDispatcher("/WEB-INF/pageModifierProduit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nouveauNom = request.getParameter("nom");
        System.out.println("NOUVEAU NOM : " + nouveauNom);
        String nom = this.produit.getNom();
        System.out.println("ANCIEN NOM DU PRODUIT MODIFI2  : " + nom);

        if(nouveauNom.equals(nom) || (!nouveauNom.equals(nom) && !existeProduit(nouveauNom)) ) {
            String description = request.getParameter("description");
            Float prix = Float.parseFloat(request.getParameter("prix"));

            Integer stock = Integer.parseInt(request.getParameter("stock"));

            //le produit est mis à jour dans la bdd
            this.produit.setNom(nouveauNom);
            this.produit.setPrix(prix);
            this.produit.setDescription(description);
            this.produit.setStock(stock);
            ProduitDAO.updateProduit(this.produit);

            //Modification de l'image
            if (request.getPart("image") != null && request.getPart("image").getSize() > 0) {
                //TODO l'image sera modifiée, on supprime la precedente
                String uploadPath = getServletContext().getRealPath("/") + "imagesProduct/";
                Part imagePart = request.getPart("image");
                String imageFileName = this.produit.getIdProduit() + ".jpeg";
                String imageFilePath = uploadPath + imageFileName;

                produit.setImagePath(imageFilePath);
                imagePart.write(imageFilePath);
                response.sendRedirect("ServletProduits");
            }

            else {
                //TODO l'image n'était pas modifié on garde la même
                response.sendRedirect("ServletProduits");
            }
        }

        else if(existeProduit(nom)){
            // L'utilisateur existe, afficher un message d'erreur
            String errorMessage = "Un produit avec ce nom existe déjà, veuillez choisir un autre nom";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
        }
    }


    private boolean existeProduit(String nomProduit){
        List<Produit> listeProduits = ProduitDAO.getListProduits();
        for (Produit produit : listeProduits) {
            if (produit.getNom().equals(nomProduit)) {

                return true;
            }
        }

        return false;
    }


}