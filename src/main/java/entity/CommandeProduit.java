package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "commande_produit", schema = "e_commerce", catalog = "")
public class CommandeProduit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_commandeProd", nullable = false)
    private int idCommandeProd;
    @Basic
    @Column(name = "id_commande", nullable = true)
    private Integer idCommande;
    @Basic
    @Column(name = "id_produit", nullable = true)
    private Integer idProduit;
    @Basic
    @Column(name = "quantite", nullable = true)
    private Integer quantite;
    @ManyToOne
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    private Commande commandeByIdCommande;
    @ManyToOne
    @JoinColumn(name = "id_produit", referencedColumnName = "id_produit")
    private Produit produitByIdProduit;

    public int getIdCommandeProd() {
        return idCommandeProd;
    }

    public void setIdCommandeProd(int idCommandeProd) {
        this.idCommandeProd = idCommandeProd;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Commande getCommandeByIdCommande() {
        return commandeByIdCommande;
    }

    public void setCommandeByIdCommande(Commande commandeByIdCommande) {
        this.commandeByIdCommande = commandeByIdCommande;
    }

    public Produit getProduitByIdProduit() {
        return produitByIdProduit;
    }

    public void setProduitByIdProduit(Produit produitByIdProduit) {
        this.produitByIdProduit = produitByIdProduit;
    }
}
