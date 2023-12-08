package ecommerce.ecommerce.model.DAO;

import ecommerce.ecommerce.HibernateUtil;
import entity.Commande;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

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

    public static int getIdFromLastCommande() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
            Root<Commande> root = criteriaQuery.from(Commande.class);
            criteriaQuery.select(criteriaBuilder.max(root.get("idCommande")));
            Integer maxId = session.createQuery(criteriaQuery).uniqueResult();
            return maxId != null ? maxId : 0;
        } catch (Exception e) {
            System.out.println("ERREUR AU RECUPERER LE MAXIMUM D'ID DE COMMANDE");
            e.printStackTrace();
            return 0;
        }
    }



}
