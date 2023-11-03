package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Client {
    private int idClient;
    private Integer idCommande;
    private String compteBancaireNum;
    private BigDecimal compteBancaireSolde;
    private String droits;
    private Integer points;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public String getCompteBancaireNum() {
        return compteBancaireNum;
    }

    public void setCompteBancaireNum(String compteBancaireNum) {
        this.compteBancaireNum = compteBancaireNum;
    }

    public BigDecimal getCompteBancaireSolde() {
        return compteBancaireSolde;
    }

    public void setCompteBancaireSolde(BigDecimal compteBancaireSolde) {
        this.compteBancaireSolde = compteBancaireSolde;
    }

    public String getDroits() {
        return droits;
    }

    public void setDroits(String droits) {
        this.droits = droits;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        if (idClient != that.idClient) return false;
        if (!Objects.equals(idCommande, that.idCommande)) return false;
        if (!Objects.equals(compteBancaireNum, that.compteBancaireNum)) return false;
        if (!Objects.equals(compteBancaireSolde, that.compteBancaireSolde)) return false;
        if (!Objects.equals(droits, that.droits)) return false;
        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + (idCommande != null ? idCommande.hashCode() : 0);
        result = 31 * result + (compteBancaireNum != null ? compteBancaireNum.hashCode() : 0);
        result = 31 * result + (compteBancaireSolde != null ? compteBancaireSolde.hashCode() : 0);
        result = 31 * result + (droits != null ? droits.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
