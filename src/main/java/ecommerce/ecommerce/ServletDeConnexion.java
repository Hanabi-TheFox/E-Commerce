package ecommerce.ecommerce;

import entity.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.IOException;
@WebServlet(name = "ServletDeConnexion", value = "/ServletDeConnexion")
public class ServletDeConnexion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Traitement pour la méthode GET (par exemple, affichage de la page de connexion)
        request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les attributs "mail" et "motDePasse" passés en paramètres
        String email = request.getParameter("mail");
        String motDePasse = request.getParameter("motDePasse");

        // Vérifier si l'utilisateur existe dans la base de données (vous devez implémenter cette partie)
        boolean utilisateurExiste = verifierUtilisateurEnBaseDeDonnees(email, motDePasse);

        if (utilisateurExiste) {
            // L'utilisateur existe, rediriger vers la page de profil
            request.getRequestDispatcher("/WEB-INF/pageProfil.jsp").forward(request, response);
        } else {
            // L'utilisateur n'existe pas, afficher un message d'erreur
            String errorMessage = "Utilisateur non trouvé, vérifiez l'identifiant et/ou le mot de passe";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConnexion.jsp").forward(request, response);
        }
    }

    private boolean verifierUtilisateurEnBaseDeDonnees(String email, String motDePasse) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Requête Hibernate pour récupérer l'utilisateur par email
            Query<Utilisateur> query = session.createQuery("FROM Utilisateur WHERE mail = :email", Utilisateur.class);
            query.setParameter("email", email);
            Utilisateur utilisateur = query.uniqueResult();

            if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {
                // L'utilisateur existe et le mot de passe correspond
                transaction.commit();
                return true;
            }

            transaction.rollback();
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}