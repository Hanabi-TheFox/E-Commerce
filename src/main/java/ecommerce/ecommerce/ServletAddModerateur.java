package ecommerce.ecommerce;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletAddModerateur", value = "/ServletAddModerateur")
public class ServletAddModerateur extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ajouterModerateur.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the registration attributes
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String droits = request.getParameter("droits");
        // Checks if the user already exists in the database
        Utilisateur utilisateur = ExisteUtilisateur(email);

        // If the user does not exist, add it to the database
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setMail(email);
            utilisateur.setMotDePasse(motDePasse);
            utilisateur.setTypeDeCompte("Moderateur");
            UtilisateurDAO.addUtilisateur(utilisateur);
            response.sendRedirect("ServletListeModerateur");

        } else {
            // User exists, display error message
            String errorMessage = "Utilisateur déjà existant, veuillez vous connecter";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
        }

    }

    //Returns the user if it exists and null otherwise
    private Utilisateur ExisteUtilisateur(String email) {
        try {
            List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
            for (Utilisateur utilisateur : listeUtilisateurs) {
                if (utilisateur.getMail() != null && utilisateur.getMail().equals(email)) {
                    // User exists and password is correct
                    return utilisateur;
                }
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
