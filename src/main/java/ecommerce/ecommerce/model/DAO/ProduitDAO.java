package ecommerce.ecommerce.model.DAO;

import java.util.ArrayList;
import java.util.List;


import entity.Produit;
import entity.Utilisateur;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import ecommerce.ecommerce.HibernateUtil;
import org.hibernate.Transaction;

public class ProduitDAO
{
    public static void addProduit(Utilisateur p)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    public static void removeProduit(int idProduit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Produit> criteriaDelete = criteriaBuilder.createCriteriaDelete(Produit.class);
            Root<Produit> root = criteriaDelete.from(Produit.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id_produit"), idProduit));

            int deletedCount = session.createQuery(criteriaDelete).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("ERREUR DE SUPPRESSION PRODUIT : " + idProduit);
            e.printStackTrace();
        }
    }

    public static List<Produit> getListProduits() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Produit> criteriaQuery = criteriaBuilder.createQuery(Produit.class);
            Root<Produit> root = criteriaQuery.from(Produit.class);
            criteriaQuery.select(root);

            return session.createQuery(criteriaQuery).list();
        } catch (Exception e) {
            System.out.println("ERREUR AU RECUPERER LISTE DE PRODUITS");
            e.printStackTrace();
            return new ArrayList<>();//liste vide si erreur
        }
    }


    public static Produit getProduitById(int idProduit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produit.class, idProduit);
        } catch (Exception e) {
            System.out.println("ERREUR LORS DE LA RECHERCHE DU PRODUIT PAR SON ID : " + idProduit);
            e.printStackTrace();
            return null;
        }
    }





    /*public static List<VUtilisateurs> getListViewUtilisateurs()
    {
        //Code à compléter
        return null;
    }*/
}
