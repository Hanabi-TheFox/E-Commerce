package ecommerce.ecommerce;

import java.io.IOException;
import java.util.List;

import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Moderateur;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletModifyRights", value = "/ServletModifyRights")
public class ServletModifyRights extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        request.getRequestDispatcher("/WEB-INF/modifierDroitsModerateur.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String droits = request.getParameter("droits");
        Utilisateur utilisateur = ExisteModerateur(email);
        if(utilisateur != null) {
            Moderateur m = UtilisateurDAO.findModByUtilisateur(utilisateur);
            UtilisateurDAO.modifyModerateurDroits(m, droits);
            Boolean modification = true;
            request.setAttribute("modification",modification);
            request.getRequestDispatcher("/WEB-INF/modifierDroitsModerateur.jsp").forward(request, response);
        }
        else {
            System.out.println("Utilisateur null verifié");
        }

    }

    private Utilisateur ExisteModerateur(String email){
        try {
            List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
            for (Utilisateur utilisateur : listeUtilisateurs) {
                if (utilisateur.getMail() != null && utilisateur.getMail().equals(email) && utilisateur.getTypeDeCompte().equals("Moderateur")) {
                    // L'utilisateur existe et le mot de passe est correct
                    System.out.println(utilisateur.getMail());
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
