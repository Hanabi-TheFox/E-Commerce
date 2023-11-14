package ecommerce.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import services.Courier;
import services.SendService;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.Utilisateur;
@WebServlet(name = "ServletDInscription", value = "/ServletDInscription")
public class ServletDInscription extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET
        request.getRequestDispatcher("/WEB-INF/pageInscription.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les attributs d'inscription
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // Vérifier si l'utilisateur existe dans la base de données
        Utilisateur utilisateur = ExisteUtilisateur(email);

        // Si l'utilisateur n'existe pas, l'ajouter dans la base de données
        if (utilisateur == null){
            utilisateur = new Utilisateur();
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setMail(email);
            utilisateur.setMotDePasse(motDePasse);
            utilisateur.setTypeDeCompte("Client");
            UtilisateurDAO.addUtilisateur(utilisateur);
            utilisateur = ExisteUtilisateur(email);
            Controller.getInstanceController().requestSetUtilisateur(utilisateur);

            // L'utilisateur à été créer, on envoie un mail de confirmation
            Courier.init("pk_prod_RW21FPAESN4DD3N8YK0RWH3YEC0E");

            SendEnhancedRequestBody request2 = new SendEnhancedRequestBody();
            SendRequestMessage message = new SendRequestMessage();

            HashMap<String, String> to = new HashMap<String, String>();
            to.put("email", email);
            message.setTo(to);
            message.setTemplate("ZHASTSX98ZM89ZGHHKTNQ8RN4P3V");
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("user", prenom+" "+nom);
            message.setData(data);
            request2.setMessage(message);
            try {
                SendEnhancedResponseBody response2 = new SendService().sendEnhancedMessage(request2);
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Rediriger vers la page de profil
// --------------------------------------------------------------------------------------------------
            // Pour aller sur profil il faut recuperer l'utilisateur que l'on a ajouté != de la variable local au dessus
            Controller.getInstanceController().requestSetClient(UtilisateurDAO.findClientByUtilisateur(utilisateur));
            Controller.getInstanceController().requestCreateCommande(Controller.getInstanceController().requestGetClient().getIdClient());
            response.sendRedirect("ServletProfil");
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