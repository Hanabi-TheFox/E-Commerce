package entity;

import java.util.Objects;

public class Moderateur {
    private int idModerateur;
    private String droits;

    public int getIdModerateur() {
        return idModerateur;
    }

    public void setIdModerateur(int idModerateur) {
        this.idModerateur = idModerateur;
    }

    public String getDroits() {
        return droits;
    }

    public void setDroits(String droits) {
        this.droits = droits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moderateur that = (Moderateur) o;

        if (idModerateur != that.idModerateur) return false;
        return Objects.equals(droits, that.droits);
    }

    @Override
    public int hashCode() {
        int result = idModerateur;
        result = 31 * result + (droits != null ? droits.hashCode() : 0);
        return result;
    }
}
