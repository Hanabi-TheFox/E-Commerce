package ecommerce.ecommerce;

import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
@WebServlet(name = "ServletPanier", value = "/ServletPanier")
public class ServletPanier extends HttpServlet {

    private List<Produit> panier = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        request.setAttribute("panier", panier);
        request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("1");

        if (action.equals("ajouter")) {
            System.out.println("2");
            // Récupérez les informations du produit à ajouter depuis le formulaire
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            String produitNom = request.getParameter("produitNom");
            BigDecimal produitPrix = new BigDecimal(request.getParameter("produitPrix"));
            int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
            System.out.println("3");
            // Ajoutez le produit au panier
            Produit produit = new Produit();
            produit.setIdProduit(produitId);
            produit.setNom(produitNom);
            produit.setPrix(produitPrix);
            produit.setStock(produitQuantite);
            System.out.println("4");

            panier.add(produit);
            System.out.println("5");
        }
        System.out.println("6");

        BigDecimal montantTotal = BigDecimal.ZERO;
        for (Produit produit : panier) {
            montantTotal = montantTotal.add(produit.getPrix().multiply(new BigDecimal(produit.getStock())));
        }

        // Configurez l'attribut "montantTotal" dans la demande
        request.setAttribute("montantTotal", montantTotal);
        // Le reste de la logique pour calculer le montant total et afficher le panier
        // Redirigez ensuite vers la page JSP "panier.jsp" pour afficher le panier
    }
}
