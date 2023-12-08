package ecommerce.ecommerce.model.DAO;

import ecommerce.ecommerce.HibernateUtil;
import entity.CommandeProduit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
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
}
