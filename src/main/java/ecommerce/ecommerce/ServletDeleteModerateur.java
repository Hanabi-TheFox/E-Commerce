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

@WebServlet(name = "ServletDeleteModerateur", value = "/ServletDeleteModerateur")
public class ServletDeleteModerateur extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/supprimerModerateur.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Utilisateur utilisateur = ExisteModerateur(email);
        if (utilisateur != null) {
            /* if user exists */
            UtilisateurDAO.deleteUtilisateur(utilisateur);
            /* delete it*/
            Boolean suppression = true;
            request.setAttribute("suppression", suppression);
            request.getRequestDispatcher("/WEB-INF/supprimerModerateur.jsp").forward(request, response);
        } else {
            System.out.println("Utilisateur null verifié");
        }

    }

    private Utilisateur ExisteModerateur(String email) {
        try {
            List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
            for (Utilisateur utilisateur : listeUtilisateurs) {
                if (utilisateur.getMail() != null && utilisateur.getMail().equals(email) && utilisateur.getTypeDeCompte().equals("Moderateur")) {
                    // User exists and password is correct
                    System.out.println(utilisateur.getMail());
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