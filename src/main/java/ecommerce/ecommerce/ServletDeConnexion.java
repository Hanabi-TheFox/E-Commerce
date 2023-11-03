package ecommerce.ecommerce;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        boolean utilisateurExiste = verifierUtilisateurEnBaseDeDonnees(email, motDePasse);
        if (utilisateurExiste) {
            // L'utilisateur existe, rediriger vers la page de profil
            HttpSession session = request.getSession(); // Créer ou récupérer une session
            //Sauvegarde utilisateur dans une session
            session.setAttribute("utilisateur", UtilisateurDAO.getUtilisateurByEmail("email")); // Stocker l'objet Utilisateur dans la session
            System.out.println("UTILISATEUR DANS LA SESSION : " + UtilisateurDAO.getUtilisateurByEmail("email"));
            response.sendRedirect("ServletProfil");
        } else {
            // L'utilisateur n'existe pas, afficher un message d'erreur
            String errorMessage = "Utilisateur non trouvé, vérifiez l'identifiant et/ou le mot de passe";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
        }
    }

    private boolean verifierUtilisateurEnBaseDeDonnees(String email, String motDePasse) {
        try {
            List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
            System.out.println("VERIFICATION UTILISATEUR DE L'UTILISATEUR" + email);
            for (Utilisateur utilisateur : listeUtilisateurs) {
                if (utilisateur.getMail() != null && utilisateur.getMail().equals(email) && utilisateur.getMotDePasse() != null && utilisateur.getMotDePasse().equals(motDePasse)) {
                    // L'utilisateur existe et le mot de passe est correct
                    System.out.println("L'UTILISATEUR EXISTE : " + utilisateur.getMail() );
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            System.out.println("L'UTILISATEUR " + email+ " N'A PAS ETE TROUVEE OU N'EXISTE PAS");
            e.printStackTrace();
            return false;
        }
    }


}