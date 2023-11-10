package ecommerce.ecommerce.model.DAO;

import java.util.ArrayList;
import java.util.List;


import entity.Moderateur;
import entity.Produit;
import entity.Utilisateur;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ecommerce.ecommerce.HibernateUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ProduitDAO
{
    public static void addProduit(Produit p)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    public static void removeProduit(int idProduit) {
        System.out.println("idproduitt :" + idProduit);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            Transaction transaction = session.beginTransaction();
            System.out.println("a");
            CriteriaDelete<Produit> deleteProduitCriteria = criteriaBuilder.createCriteriaDelete(Produit.class);
            Root<Produit> produitRoot = deleteProduitCriteria.from(Produit.class);
            System.out.println("b");
            deleteProduitCriteria.where(criteriaBuilder.equal(produitRoot.get("idProduit"), idProduit));
            System.out.println("c");
            int rowsAffected = session.createQuery(deleteProduitCriteria).executeUpdate();
            transaction.commit();
            System.out.println("d");

            if (rowsAffected == 0) {
                System.out.println("Aucun produit supprimé. L'ID du produit peut ne pas exister dans la base de données.");
            }
        } catch (HibernateException e) {
            System.out.println("Erreur Hibernate lors de la suppression du produit.");
            e.printStackTrace();
            System.out.println("e");
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

    public static Produit getProduitByName(String nomProduit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produit.class, nomProduit);
        } catch (Exception e) {
            System.out.println("ERREUR LORS DE LA RECHERCHE DU PRODUIT PAR SON NOM : " + nomProduit);
            e.printStackTrace();
            return null;
        }
    }

    public static int findIdProductdByProduct(Produit produit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM Produit WHERE nom = :nomProduit";
        Query<Produit> query = session.createQuery(hql, Produit.class);
        query.setParameter("nomProduit", produit.getNom());

        Produit produit2 = query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return produit2.getIdProduit();
    }



    /*public static List<VUtilisateurs> getListViewUtilisateurs()
    {
        //Code à compléter
        return null;
    }*/
}
