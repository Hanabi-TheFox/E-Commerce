package ecommerce.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
@WebServlet(name = "ServletAddModerateur", value = "/ServletAddModerateur")
public class ServletAddModerateur extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        request.getRequestDispatcher("/WEB-INF/ajouterModerateur.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les attributs d'inscription
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String droits = request.getParameter("droits");
        // Vérifier si l'utilisateur existe dans la base de données
        Utilisateur utilisateur = ExisteUtilisateur(email);

        // Si l'utilisateur n'existe pas, l'ajouter dans la base de données
        if (utilisateur == null){
            utilisateur = new Utilisateur();
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setMail(email);
            utilisateur.setMotDePasse(motDePasse);
            utilisateur.setTypeDeCompte("Moderateur");
            UtilisateurDAO.addUtilisateur(utilisateur);

            response.sendRedirect("ServletListeModerateur");
        } else {
            // L'utilisateur existe, afficher un message d'erreur
            String errorMessage = "Utilisateur déjà existant, veuillez vous connecter";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
        }

    }
    //Retourne l'utilisateur s'il existe et null sinon
    private Utilisateur ExisteUtilisateur(String email){
        try {
            List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
            for (Utilisateur utilisateur : listeUtilisateurs) {
                if (utilisateur.getMail() != null && utilisateur.getMail().equals(email)) {
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
