package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletPanier", value = "/ServletPanier")
public class ServletPanier extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        //Commande panier = new Commande();

        String action = request.getParameter("action");
        if (action == null) { // action is null if we click on header button 'Panier'
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        } else {
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            List<Produit> panier = Controller.getInstanceController().requestGetPanier();
            switch (action) {
                case "ajouter": {
                    int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
                    boolean produitExisteDeja = false;
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) { // le produit existe deja dans le panier
                            if (!stockIsReach(p, produitQuantite)) {
                                ajouterPrix(p);
                                p.setStock(p.getStock() + produitQuantite);
                                Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            }
                            // Marquer un message pour dire que le stock a été atteint => pas d'ajout dans le panier
                            produitExisteDeja = true;
                            break;
                        }
                    }
                    if (!produitExisteDeja) {
                        Produit produit = ProduitDAO.getProduitById(produitId);
                        if (produit != null) {
                            produit.setStock(produitQuantite);
                            ajouterPrix(produit);
                        }
                        Controller.getInstanceController().requestGetCommande().ajouterProduitDansPanier(produit);
                    }
                    request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                    break;
                }
                case "supprimer":
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) {
                            panier.remove(p);
                            Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            supprimerPrix(p);
                            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                            break;
                        }
                    }
                    break;
                case "modifier": {
                    int produitQuantite = Integer.parseInt(request.getParameter("produitQuantite"));
                    for (Produit p : panier) {
                        if (p.getIdProduit() == produitId) {
                            supprimerPrix(p);
                            p.setStock(produitQuantite);
                            ajouterPrix(p);
                            Controller.getInstanceController().requestGetCommande().setPanier(panier);
                            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
                            break;
                        }
                    }
                    break;
                }
            }
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("vider")) {
            Controller.getInstanceController().requestViderPanier();
            Controller.getInstanceController().requestGetCommande().setPrix(0);
            request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        } else if (action.equals("payer")) {
            if (Controller.getInstanceController().requestGetCommande().getPanier().isEmpty()) {
                //Aucun produit a été ajoutée pour acheter et payer
                Boolean panierVide = true;
                request.setAttribute("panierVide", panierVide);
                request.getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
        }
    }

    private void ajouterPrix(Produit p) {
        float prix = Controller.getInstanceController().requestGetCommande().getPrix();
        Controller.getInstanceController().requestGetCommande().setPrix(prix + (p.getPrix() * p.getStock()));
    }

    private void supprimerPrix(Produit p) {
        float prix = Controller.getInstanceController().requestGetCommande().getPrix();
        Controller.getInstanceController().requestGetCommande().setPrix(prix - (p.getPrix() * p.getStock()));
    }

    private boolean stockIsReach(Produit produit, int stock) {
        List<Produit> prods = ProduitDAO.getListProduits();
        for (Produit p : prods) {
            if (p.getIdProduit() == produit.getIdProduit()) {
                return produit.getStock() + stock > p.getStock();
            }
        }
        return false;
    }
}
