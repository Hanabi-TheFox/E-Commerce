package entity;

import java.util.Objects;

public class Utilisateur {
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String mail;
    private String motDePasse;
    private String typeDeCompte;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTypeDeCompte() {
        return typeDeCompte;
    }

    public void setTypeDeCompte(String typeDeCompte) {
        this.typeDeCompte = typeDeCompte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        if (idUtilisateur != that.idUtilisateur) return false;
        if (!Objects.equals(nom, that.nom)) return false;
        if (!Objects.equals(prenom, that.prenom)) return false;
        if (!Objects.equals(mail, that.mail)) return false;
        if (!Objects.equals(motDePasse, that.motDePasse)) return false;
        return Objects.equals(typeDeCompte, that.typeDeCompte);
    }

    @Override
    public int hashCode() {
        int result = idUtilisateur;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        result = 31 * result + (typeDeCompte != null ? typeDeCompte.hashCode() : 0);
        return result;
    }
}
