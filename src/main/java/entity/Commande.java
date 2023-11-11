package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Commande {
    private int idCommande;
    private Integer idClient;
    private BigDecimal prix;
    private String status;

    private List<Produit> panier = new ArrayList<>();

    ///Methode pour ajouter des produits dans le panier
    public void ajouterProduitDansPanier(Produit p){
        panier.add(p);
    }

    public void supprimerProduitDansPanier(Produit p){
        panier.remove(p);
    }

    public List<Produit> getPanier() {
        return this.panier;
    }
    public void setPanier(List<Produit> panier) {
        this.panier = panier;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commande that = (Commande) o;

        if (idCommande != that.idCommande) return false;
        if (!Objects.equals(idClient, that.idClient)) return false;
        if (!Objects.equals(prix, that.prix)) return false;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = idCommande;
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}