package entity;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_utilisateur", nullable = false)
    private int idUtilisateur;
    @Basic
    @Column(name = "nom", nullable = false, length = 20)
    private String nom;
    @Basic
    @Column(name = "prenom", nullable = false, length = 20)
    private String prenom;
    @Basic
    @Column(name = "mail", nullable = false, length = 30)
    private String mail;
    @Basic
    @Column(name = "motDePasse", nullable = false, length = 20)
    private String motDePasse;
    @Basic
    @Column(name = "TypeDeCompte", nullable = false)
    private Object typeDeCompte;
    @OneToOne(mappedBy = "utilisateurByIdClient")
    private Client clientByIdUtilisateur;

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

    public Object getTypeDeCompte() {
        return typeDeCompte;
    }

    public void setTypeDeCompte(Object typeDeCompte) {
        this.typeDeCompte = typeDeCompte;
    }

    public Client getClientByIdUtilisateur() {
        return clientByIdUtilisateur;
    }

    public void setClientByIdUtilisateur(Client clientByIdUtilisateur) {
        this.clientByIdUtilisateur = clientByIdUtilisateur;
    }
}
