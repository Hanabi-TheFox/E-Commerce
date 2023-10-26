package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Commande {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_commande", nullable = false)
    private int idCommande;
    @Basic
    @Column(name = "prix", nullable = false, precision = 2)
    private BigDecimal prix;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;
    @OneToMany(mappedBy = "commandeByIdCommande")
    private Collection<Client> clientsByIdCommande;
    @OneToMany(mappedBy = "commandeByIdCommande")
    private Collection<CommandeProduit> commandeProduitsByIdCommande;

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Client> getClientsByIdCommande() {
        return clientsByIdCommande;
    }

    public void setClientsByIdCommande(Collection<Client> clientsByIdCommande) {
        this.clientsByIdCommande = clientsByIdCommande;
    }

    public Collection<CommandeProduit> getCommandeProduitsByIdCommande() {
        return commandeProduitsByIdCommande;
    }

    public void setCommandeProduitsByIdCommande(Collection<CommandeProduit> commandeProduitsByIdCommande) {
        this.commandeProduitsByIdCommande = commandeProduitsByIdCommande;
    }
}
