package entity;

import java.util.Objects;

public class CommandeProduit {
    private int idCommandeProd;
    private Integer idCommande;
    private Integer idProduit;
    private Integer quantite;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandeProduit that = (CommandeProduit) o;

        if (idCommandeProd != that.idCommandeProd) return false;
        if (!Objects.equals(idCommande, that.idCommande)) return false;
        if (!Objects.equals(idProduit, that.idProduit)) return false;
        return Objects.equals(quantite, that.quantite);
    }

    @Override
    public int hashCode() {
        int result = idCommandeProd;
        result = 31 * result + (idCommande != null ? idCommande.hashCode() : 0);
        result = 31 * result + (idProduit != null ? idProduit.hashCode() : 0);
        result = 31 * result + (quantite != null ? quantite.hashCode() : 0);
        return result;
    }
}
