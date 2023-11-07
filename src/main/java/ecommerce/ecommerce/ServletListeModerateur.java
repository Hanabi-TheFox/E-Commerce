package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import entity.Utilisateur;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ServletListeModerateur", value = "/ServletListeModerateur")
public class ServletListeModerateur extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        List<Utilisateur> listUtilisateurs = Controller.getInstanceController().requestGetListUtilisateurs();
        List<Utilisateur> listModerateur = new ArrayList<Utilisateur>();
        for(Utilisateur utilisateur : listUtilisateurs){
            String typeCompte = utilisateur.getTypeDeCompte();
            if(Objects.equals(typeCompte, "Moderateur")){
                listModerateur.add(utilisateur);
            }
        }
        request.setAttribute("listModerateur", listModerateur);
        request.getRequestDispatcher("/WEB-INF/pageListeModerateur.jsp").forward(request, response);
    }


}
