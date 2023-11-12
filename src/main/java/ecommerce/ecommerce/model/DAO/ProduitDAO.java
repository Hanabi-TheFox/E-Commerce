package ecommerce.ecommerce.model.DAO;

import java.util.ArrayList;
import java.util.List;


import entity.Client;
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

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            Transaction transaction = session.beginTransaction();
            CriteriaDelete<Produit> deleteProduitCriteria = criteriaBuilder.createCriteriaDelete(Produit.class);
            Root<Produit> produitRoot = deleteProduitCriteria.from(Produit.class);
            deleteProduitCriteria.where(criteriaBuilder.equal(produitRoot.get("idProduit"), idProduit));
            int rowsAffected = session.createQuery(deleteProduitCriteria).executeUpdate();
            transaction.commit();

            if (rowsAffected == 0) {
                System.out.println("Aucun produit supprimé. L'ID du produit peut ne pas exister dans la base de données.");
            }
        } catch (HibernateException e) {
            System.out.println("Erreur Hibernate lors de la suppression du produit.");
            e.printStackTrace();
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

    public static void updateProduct(Produit p) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            // Récupérer le client existant de la base de données
            Produit produitExistant = session.get(Produit.class, p.getIdProduit());

            // Mettre à jour les propriétés spécifiques du client existant
            produitExistant.setStock(p.getStock());

            // Mettre à jour le client dans la base de données
            session.update(produitExistant);

            session.getTransaction().commit();
        } catch (Exception ex) {
            // Gérer les exceptions ici (enregistrement des journaux, etc.)
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

}
