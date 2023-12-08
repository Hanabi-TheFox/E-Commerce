package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletDeConnexion", value = "/ServletDeConnexion")
public class ServletDeConnexion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les attributs "mail" et "motDePasse" passés en paramètres
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Vérifier si l'utilisateur existe dans la base de données (vous devez implémenter cette partie)
        Utilisateur utilisateur = verifierUtilisateurEnBaseDeDonnees(email, motDePasse);
        if (utilisateur != null) {
            // L'utilisateur existe, rediriger vers la page de profil
            //Il est utilisée le MVC pour sauvagarder les données de l'utilisateur en question
            Controller.getInstanceController().requestSetUtilisateur(utilisateur);

            //TODO il est geré les differentes types de comptes selon l'utilisateur:
            //TODO pour le client, une commande vide est crée
            if (utilisateur.getTypeDeCompte().equals("Client")) {
                Controller.getInstanceController().requestSetClient(UtilisateurDAO.findClientByUtilisateur(utilisateur));
                Controller.getInstanceController().requestCreateCommande(Controller.getInstanceController().requestGetClient().getIdClient());
            }
            response.sendRedirect("ServletProduits");

        } else {
            // L'utilisateur n'existe pas, afficher un message d'erreur
            String errorMessage = "Utilisateur non trouvé, vérifiez l'identifiant et/ou le mot de passe";
            request.setAttribute("client", Controller.getInstanceController().requestGetClient());
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
        }
    }

    //Retourne l'utilisateur s'il existe et null sinon
    private Utilisateur verifierUtilisateurEnBaseDeDonnees(String email, String motDePasse) {
        try {
            List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
            for (Utilisateur utilisateur : listeUtilisateurs) {
                if (utilisateur.getMail() != null && utilisateur.getMail().equals(email) && utilisateur.getMotDePasse() != null && utilisateur.getMotDePasse().equals(motDePasse)) {
                    // L'utilisateur existe et le mot de passe est correct
                    return utilisateur;
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println("L'UTILISATEUR " + email + " N'A PAS ETE TROUVEE OU N'EXISTE PAS");
            e.printStackTrace();
            return null;
        }
    }


}