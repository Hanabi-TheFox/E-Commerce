package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Produit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_produit", nullable = false)
    private int idProduit;
    @Basic
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    @Basic
    @Column(name = "prix", nullable = false, precision = 2)
    private BigDecimal prix;
    @Basic
    @Column(name = "stock", nullable = true)
    private Integer stock;
    @OneToMany(mappedBy = "produitByIdProduit")
    private Collection<CommandeProduit> commandeProduitsByIdProduit;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Collection<CommandeProduit> getCommandeProduitsByIdProduit() {
        return commandeProduitsByIdProduit;
    }

    public void setCommandeProduitsByIdProduit(Collection<CommandeProduit> commandeProduitsByIdProduit) {
        this.commandeProduitsByIdProduit = commandeProduitsByIdProduit;
    }
}
