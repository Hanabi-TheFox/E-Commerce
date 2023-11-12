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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        if(existeProduit(nom)){
                // L'utilisateur existe, afficher un message d'erreur
                String errorMessage = "Un produit avec ce nom existe déjà, veuillez choisir un autre nom";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/ajouterProduit.jsp").forward(request, response);
        }else {
            String description = request.getParameter("description");
            Float prix = Float.parseFloat(request.getParameter("prix"));

            Integer stock = Integer.parseInt(request.getParameter("stock"));

            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setPrix(prix);
            produit.setDescription(description);
            produit.setStock(stock);

            ProduitDAO.addProduit(produit);

            //le produit est ajouté dans la bdd pour qu'un id lui soit attribué
            int idImage = ProduitDAO.findIdProductdByProduct(produit); //cet id est recuperée
            System.out.println("Working Directory = " + System.getProperty("user.dir"));


            String relativePath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "imagesProduct" + File.separator;

            System.out.println("RELATIVE PATH : " + relativePath);
            //String absolutePath = getServletContext().getRealPath(relativePath);
            //System.out.println("CHEMIN IMAGE : " + absolutePath);
            Part imagePart = request.getPart("image");
            System.out.println(imagePart);

            //------------EXTENTION DE L'IMAGE

            // Obtenez le type MIME de la partie (image)
            String contentType = imagePart.getContentType();

// Déduisez l'extension à partir du type MIME
            String fileExtension = null;
            if (contentType != null && contentType.startsWith("image/")) {
                fileExtension = contentType.substring("image/".length());
            }

// fileExtension contient maintenant l'extension de l'image (par exemple, "png", "jpeg", etc.)
            System.out.println("Extension de l'image : " + fileExtension);
            //-----------------------------------


            String imageFileName = idImage + "." + fileExtension;
            System.out.println("Image FILE NAME" + imageFileName);
            String imageFilePath = relativePath + imageFileName;
            System.out.println("Image PATH FILE ajouté : " + imageFilePath );

            produit.setImagePath(imageFilePath);
            //imagePart.write(imageFilePath);

            //--------ENREGISTREMENT DE L'IMAGE SANS WRITE (car il passe vers le repertoire de compilation work

            try (InputStream inputStream = imagePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(imageFilePath)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }



            //------------------------------------------------------------




            // Redirigez l'utilisateur vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("ServletProduits");
        }
    }

    private String getExtension(Part part) {
        //TODO recupere l'extention de l'image
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('.') + 1);
            }
        }
        return null;
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
