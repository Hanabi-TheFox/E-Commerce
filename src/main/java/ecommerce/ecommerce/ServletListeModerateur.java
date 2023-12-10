package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ServletListeModerateur", value = "/ServletListeModerateur")
public class ServletListeModerateur extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Utilisateur> listUtilisateurs = Controller.getInstanceController().requestGetListUtilisateurs();
        List<Utilisateur> listModerateur = new ArrayList<Utilisateur>();
        /* we check for each user if it's a moderator, the nwe add it to the list */
        for (Utilisateur utilisateur : listUtilisateurs) {
            String typeCompte = utilisateur.getTypeDeCompte();
            if (Objects.equals(typeCompte, "Moderateur")) {
                listModerateur.add(utilisateur);
            }
        }
        request.setAttribute("listModerateur", listModerateur);
        request.getRequestDispatcher("/WEB-INF/pageListeModerateur.jsp").forward(request, response);
    }


}
