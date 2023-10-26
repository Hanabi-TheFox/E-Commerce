package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_client", nullable = false)
    private int idClient;
    @Basic
    @Column(name = "id_commande", nullable = true)
    private Integer idCommande;
    @Basic
    @Column(name = "compteBancaireNum", nullable = false, length = 19)
    private String compteBancaireNum;
    @Basic
    @Column(name = "compteBancaireSolde", nullable = true, precision = 2)
    private BigDecimal compteBancaireSolde;
    @Basic
    @Column(name = "droits", nullable = false, length = 5)
    private String droits;
    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_utilisateur", nullable = false)
    private Utilisateur utilisateurByIdClient;
    @ManyToOne
    @JoinColumn(name = "id_commande", referencedColumnName = "id_commande")
    private Commande commandeByIdCommande;

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

    public Utilisateur getUtilisateurByIdClient() {
        return utilisateurByIdClient;
    }

    public void setUtilisateurByIdClient(Utilisateur utilisateurByIdClient) {
        this.utilisateurByIdClient = utilisateurByIdClient;
    }

    public Commande getCommandeByIdCommande() {
        return commandeByIdCommande;
    }

    public void setCommandeByIdCommande(Commande commandeByIdCommande) {
        this.commandeByIdCommande = commandeByIdCommande;
    }
}
