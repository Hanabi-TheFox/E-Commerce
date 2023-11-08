package ecommerce.ecommerce.model.DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import entity.Moderateur;
import entity.Utilisateur;
import entity.Client;
import jakarta.persistence.criteria.*;
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
            }else { // typeDeCompte.equals("Client")
                Client client = new Client();
                client.setIdClient(utilisateur.getIdUtilisateur());
                client.setCompteBancaireNum("0000 0000 0000 0000");
                client.setCompteBancaireSolde(BigDecimal.valueOf(0));
                client.setPoints(0);
                session.save(client);
                session.getTransaction().commit();
            }
        }
        session.close();
    }


/*    public static void deleteUtilisateur2(Utilisateur utilisateur) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            if (utilisateur != null) {
                session.delete(utilisateur);
                session.getTransaction().commit();
                session.beginTransaction();

                if (utilisateur.getTypeDeCompte().equals("Moderateur")) {
                    Moderateur moderateur = findModByUtilisateur(utilisateur);
                    session.delete(moderateur);
                } else if (utilisateur.getTypeDeCompte().equals("Client")) {
                    Client client = findClientByUtilisateur(utilisateur);
                    session.delete(client);
                }
                session.getTransaction().commit();
            }
        }catch (Exception e) {
            System.out.println("Erreur lors de la suppression du modérateur.");
            e.printStackTrace();
        }
    }*/
    public static void deleteUtilisateur(Utilisateur utilisateur) {
        int utilisateurId = utilisateur.getIdUtilisateur();
        String typeDeCompte = utilisateur.getTypeDeCompte();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Utilisateur> deleteCriteria = criteriaBuilder.createCriteriaDelete(Utilisateur.class);
            Root<Utilisateur> root = deleteCriteria.from(Utilisateur.class);

            deleteCriteria.where(criteriaBuilder.equal(root.get("idUtilisateur"), utilisateurId));

            Transaction transaction = session.beginTransaction();

            int deletedCount = session.createQuery(deleteCriteria).executeUpdate();

            if (deletedCount > 0) {
                if (typeDeCompte.equals("Client")){
                    CriteriaDelete<Client> deleteClientCriteria = criteriaBuilder.createCriteriaDelete(Client.class);
                    Root<Client> clientRoot = deleteClientCriteria.from(Client.class);
                    deleteClientCriteria.where(criteriaBuilder.equal(clientRoot.get("idClient"), utilisateurId));
                    session.createQuery(deleteClientCriteria).executeUpdate();
                }else if (typeDeCompte.equals("Moderateur")){
                    CriteriaDelete<Moderateur> deleteModerateurCriteria = criteriaBuilder.createCriteriaDelete(Moderateur.class);
                    Root<Moderateur> moderateurRoot = deleteModerateurCriteria.from(Moderateur.class);
                    deleteModerateurCriteria.where(criteriaBuilder.equal(moderateurRoot.get("idModerateur"), utilisateurId));
                    session.createQuery(deleteModerateurCriteria).executeUpdate();
                }
                transaction.commit();
                System.out.println("Utilisateur supprimé avec succès.");
            } else {
                transaction.rollback();
                System.out.println("Utilisateur introuvable avec l'ID : " + utilisateurId);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur.");
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

    public static void modifyModerateurDroits(int moderatorId, String nouveauxDroits) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Moderateur> updateCriteria = criteriaBuilder.createCriteriaUpdate(Moderateur.class);
            Root<Moderateur> root = updateCriteria.from(Moderateur.class);

            // il est specifiée quel attribut sera modifiée
            updateCriteria.set(root.get("droits"), nouveauxDroits);

            // on trouve cet attribut grace à l'id_moderateur correspondant
            updateCriteria.where(criteriaBuilder.equal(root.get("idModerateur"), moderatorId));

            Transaction transaction = session.beginTransaction();
            int updatedCount = session.createQuery(updateCriteria).executeUpdate();
            transaction.commit();

            if (updatedCount > 0) {
                System.out.println("Droits du modérateur mis à jour avec succès.");
            } else {
                System.out.println("Modérateur introuvable avec l'ID : " + moderatorId);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour des droits du modérateur.");
            e.printStackTrace();
        }
    }


    /*public static void modifyModerator(Moderateur moderateur, String droits){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        moderateur.setDroits(droits);
        System.out.println(moderateur.getDroits());
        session.save(moderateur);
        session.getTransaction().commit();
        session.close();
    }*/
}
