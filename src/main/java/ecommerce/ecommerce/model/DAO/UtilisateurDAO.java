package ecommerce.ecommerce.model.DAO;

import java.util.ArrayList;
import java.util.List;


import entity.Utilisateur;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import ecommerce.ecommerce.HibernateUtil;
import org.hibernate.Transaction;

public class UtilisateurDAO
{
    public static void addUtilisateur(Utilisateur e)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
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


    /*public static List<VUtilisateurs> getListViewUtilisateurs()
    {
        //Code à compléter
        return null;
    }*/
}