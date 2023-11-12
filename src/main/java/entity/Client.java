package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Client {
    private int idClient;
    private String compteBancaireNum;
    private BigDecimal compteBancaireSolde;
    private Integer points;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    //Cela pose un probleme, laissant le Client = null lors de la connexion, A VOIR
    /*public void setCompteBancaireSolde(BigDecimal compteBancaireSolde) {
        // Vérification si le solde est supérieur ou égal à zéro
        if (compteBancaireSolde.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le solde du compte bancaire doit être supérieur ou égal à zéro.");
        }

        this.compteBancaireSolde = compteBancaireSolde;
    } */


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        // Vérification si les points sont supérieurs ou égaux à zéro
        if (points < 0) {
            throw new IllegalArgumentException("Les points doivent être supérieurs ou égaux à zéro.");
        }

        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        if (idClient != that.idClient) return false;
        if (!Objects.equals(compteBancaireNum, that.compteBancaireNum)) return false;
        if (!Objects.equals(compteBancaireSolde, that.compteBancaireSolde)) return false;
        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + (compteBancaireNum != null ? compteBancaireNum.hashCode() : 0);
        result = 31 * result + (compteBancaireSolde != null ? compteBancaireSolde.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }
}
