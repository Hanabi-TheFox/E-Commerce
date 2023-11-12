package ecommerce.ecommerce.model.DAO;

import entity.CommandeProduit;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import ecommerce.ecommerce.HibernateUtil;
import org.hibernate.Transaction;

public class CommandeProduitDAO
{
    public static void addCommandeProduit(CommandeProduit c)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    public static void removeCommande(int idCommandeProduit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<CommandeProduit> criteriaDelete = criteriaBuilder.createCriteriaDelete(CommandeProduit.class);
            Root<CommandeProduit> root = criteriaDelete.from(CommandeProduit.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id_commandeProd"), idCommandeProduit));

            int deletedCount = session.createQuery(criteriaDelete).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println("ERREUR DE SUPPRESSION COMMANDE : " + idCommandeProduit);
            e.printStackTrace();
        }
    }

    public static CommandeProduit getCommandeProduitById(int idCommandeProd) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(CommandeProduit.class, idCommandeProd);
        } catch (Exception e) {
            System.out.println("ERREUR LORS DE LA RECHERCHE DU PRODUIT PAR SON ID : " + idCommandeProd);
            e.printStackTrace();
            return null;
        }
    }

}
