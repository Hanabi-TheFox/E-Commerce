package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Produit {
    private int idProduit;
    private String nom;
    private Float prix;
    private String description;
    private Integer stock;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    //TODO: L'image du produit se recupere dans le jsp pageProduits
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

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produit that = (Produit) o;

        if (idProduit != that.idProduit) return false;
        if (!Objects.equals(nom, that.nom)) return false;
        if (!Objects.equals(prix, that.prix)) return false;
        if (!Objects.equals(description, that.description)) return false;
        return Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        int result = idProduit;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }
}
