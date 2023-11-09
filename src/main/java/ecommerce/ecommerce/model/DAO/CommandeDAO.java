package ecommerce.ecommerce.model.DAO;

import entity.Commande;
import entity.Produit;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import ecommerce.ecommerce.HibernateUtil;
import org.hibernate.Transaction;

public class CommandeDAO
{
    public static void addCommande(Commande c)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    public static void removeCommande(int idCommande) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Commande> criteriaDelete = criteriaBuilder.createCriteriaDelete(Commande.class);
            Root<Commande> root = criteriaDelete.from(Commande.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id_commande"), idCommande));

            int deletedCount = session.createQuery(criteriaDelete).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("ERREUR DE SUPPRESSION COMMANDE : " + idCommande);
            e.printStackTrace();
        }
    }


    public static Commande getCommandeById(int idCommande) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Commande.class, idCommande);
        } catch (Exception e) {
            System.out.println("ERREUR LORS DE LA RECHERCHE DU PRODUIT PAR SON ID : " + idCommande);
            e.printStackTrace();
            return null;
        }
    }

}
