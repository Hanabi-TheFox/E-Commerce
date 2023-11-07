package ecommerce.ecommerce.model.DAO;

import java.util.ArrayList;
import java.util.List;


import entity.Moderateur;
import entity.Utilisateur;
import entity.Client;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import ecommerce.ecommerce.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UtilisateurDAO
{
    public static void addUtilisateur(Utilisateur e)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();

        String typeDeCompte = e.getTypeDeCompte();

        if(!typeDeCompte.equals("Admin")){
            session.beginTransaction();
            String hql = "FROM Utilisateur WHERE mail = :email";
            Query<Utilisateur> query = session.createQuery(hql, Utilisateur.class);
            query.setParameter("email", e.getMail());
            Utilisateur utilisateur = query.uniqueResult();

            if(typeDeCompte.equals("Moderateur")){
                Moderateur moderateur = new Moderateur();
                moderateur.setIdModerateur(utilisateur.getIdUtilisateur());
                moderateur.setDroits("000");
                session.save(moderateur);
                session.getTransaction().commit();

            }
        }
        session.close();
    }


    public static void removeUtilisateur(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Utilisateur> criteriaDelete = criteriaBuilder.createCriteriaDelete(Utilisateur.class);
            Root<Utilisateur> root = criteriaDelete.from(Utilisateur.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("mail"), email));

            int deletedCount = session.createQuery(criteriaDelete).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("ERREUR DE SUPPRESION UTILISATEUR : " + email);
            e.printStackTrace();

        }
    }


    public static List<Utilisateur> getListUtilisateurs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> criteriaQuery = criteriaBuilder.createQuery(Utilisateur.class);
            Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
            criteriaQuery.select(root);

            return session.createQuery(criteriaQuery).list();
        } catch (Exception e) {
            System.out.println("ERREUR AU RECUPERER LISTE D'UTILISATEURS");
            e.printStackTrace();
            return new ArrayList<>(); //une liste vide est retournée si erreur
        }
    }

    public static Utilisateur getUtilisateurByEmail(String email) {
        List<Utilisateur> listeUtilisateurs = UtilisateurDAO.getListUtilisateurs();
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getMail() != null && utilisateur.getMail().equals(email)) {
                // L'utilisateur existe et le mot de passe est correct
                return utilisateur;
            }
        }
            return null; //l'utilisateur n'a pas été trouvée par son mail

    }
    public static Client findClientByUtilisateur(Utilisateur utilisateur) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM Client WHERE idClient = :userId";
        Query<Client> query = session.createQuery(hql, Client.class);
        query.setParameter("userId", utilisateur.getIdUtilisateur());

        Client client = query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return client;
    }

    public static Moderateur findModByUtilisateur(Utilisateur utilisateur) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM Moderateur WHERE idModerateur = :userId";
        Query<Moderateur> query = session.createQuery(hql, Moderateur.class);
        query.setParameter("userId", utilisateur.getIdUtilisateur());

        Moderateur moderateur = query.uniqueResult();
        System.out.println(moderateur.getDroits());
        session.getTransaction().commit();
        session.close();

        return moderateur;
    }
}
