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
        request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves form data
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Checks if user already exists
        Utilisateur utilisateur = verifierUtilisateurEnBaseDeDonnees(email, motDePasse);
        if (utilisateur != null) {
            Controller.getInstanceController().requestSetUtilisateur(utilisateur);
            // if it's a client then connect as a client
            if (utilisateur.getTypeDeCompte().equals("Client")) {
                Controller.getInstanceController().requestSetClient(UtilisateurDAO.findClientByUtilisateur(utilisateur));
                Controller.getInstanceController().requestCreateCommande(Controller.getInstanceController().requestGetClient().getIdClient());
            }
            response.sendRedirect("ServletProduits");

        } else {
            // User doesn't exist, errorMessage
            String errorMessage = "Utilisateur non trouvé, vérifiez l'identifiant et/ou le mot de passe";
            request.setAttribute("client", Controller.getInstanceController().requestGetClient());
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
        }
    }

    private Utilisateur verifierUtilisateurEnBaseDeDonnees(String email, String motDePasse) {
        try {
            List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
            for (Utilisateur utilisateur : listeUtilisateurs) {
                if (utilisateur.getMail() != null && utilisateur.getMail().equals(email) && utilisateur.getMotDePasse() != null && utilisateur.getMotDePasse().equals(motDePasse)) {
                    // User exists and password is correct
                    return utilisateur;
                }
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}