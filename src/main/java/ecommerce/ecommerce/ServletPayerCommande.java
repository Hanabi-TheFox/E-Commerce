package ecommerce.ecommerce;

import ecommerce.ecommerce.controller.Controller;
import ecommerce.ecommerce.model.DAO.CommandeDAO;
import ecommerce.ecommerce.model.DAO.CommandeProduitDAO;
import ecommerce.ecommerce.model.DAO.ProduitDAO;
import ecommerce.ecommerce.model.DAO.UtilisateurDAO;
import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "ServletPayerCommande", value = "/ServletPayerCommande")
public class ServletPayerCommande extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carte = request.getParameter("numeroCarte");
        String errorMessage="";
        Client client = Controller.getInstanceController().requestGetClient();
        if(client.getCompteBancaireNum().equals("0000 0000 0000 000")){ // si le client n'a pas de CB
            errorMessage = "Vous devez d'abord ajouter une carte bancaire ";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
        }
        else if(carte.equals(client.getCompteBancaireNum())){
            Commande commande = Controller.getInstanceController().requestGetCommande();
            List<Produit> panier = commande.getPanier();
            if(client.getCompteBancaireSolde().floatValue() >= commande.getPrix()){
                List<Produit> listeProduits = Controller.getInstanceController().requestGetProduits();
                acceptPayment(commande, panier, listeProduits);
                mailDuPayment(panier);
                request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
            }else {
                errorMessage = "Solde trop faible (solde actuel = " + client.getCompteBancaireSolde() + ")";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
            }
        }else {
            System.out.println(client.getCompteBancaireNum());
            System.out.println(carte);
            errorMessage = "Numéro de carte incorrect";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/pageConfirmerPayement.jsp").forward(request, response);
        }
    }


    private void acceptPayment(Commande commande, List<Produit> panier, List<Produit> prods){
        // Manque plus que gérer les points de fidélités et soustraire le prix de la commande au solde actuel du client

        // create Commande entity in our bdd
        Commande commandeBDD = new Commande();
        commandeBDD.setIdClient(commande.getIdClient());
        commandeBDD.setPrix(commande.getPrix());
        commandeBDD.setStatus("payé");
        CommandeDAO.addCommande(commandeBDD);
        // we need the id of the last 'Commande' entity in order to create a 'CommandeProduit' entity
        int idCommandeBDD = CommandeDAO.getIdFromLastCommande();
        CommandeProduit panierBDD = new CommandeProduit();
        for (Produit produit : panier){
            // change stock
            for(Produit p : prods){
                if (p.getIdProduit() == produit.getIdProduit()){
                    p.setStock(p.getStock() - produit.getStock());
                    ProduitDAO.updateProduct(p);
                    break;
                }
            }

            // add CommandeProduit entity in our bdd
            panierBDD.setIdCommande(idCommandeBDD);
            panierBDD.setIdProduit(produit.getIdProduit());
            panierBDD.setQuantite(produit.getStock());
            CommandeProduitDAO.addCommandeProduit(panierBDD);
        }
        Controller.getInstanceController().requestCreateCommande(commande.getIdClient());
        // maj du solde client
        Utilisateur user = Controller.getInstanceController().requestGetUtilisateur();
        Client client = UtilisateurDAO.findClientByUtilisateur(user);
        assert client != null;
        float newSolde = client.getCompteBancaireSolde().floatValue() - commande.getPrix();
        client.setCompteBancaireSolde(new BigDecimal(newSolde));

        // Point de fidélité
        int pointsFidelite = (int) (Math.floor(commande.getPrix()) / 10);
        client.setPoints(client.getPoints()+pointsFidelite);

        UtilisateurDAO.updateClient(client);
    }

    private void mailDuPayment(List<Produit> panier){
        Utilisateur user = Controller.getInstanceController().requestGetUtilisateur();
        Courier.init("pk_prod_RW21FPAESN4DD3N8YK0RWH3YEC0E");
        String COMPANY_NAME = "Azur Shop";

        // Récupération des informations nécessaires pour construire l'email
        String userName = user.getPrenom() + " " + user.getNom(); // Fonction pour obtenir le nom du client
        // Génération de la date et de l'heure actuelles
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        // Formatage de la date et de l'heure
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String paymentDate = currentDate.format(dateFormatter);
        String paymentTime = currentTime.format(timeFormatter);
        String currency = "EUR"; // Changez en la devise appropriée si nécessaire

        // Préparation du contenu de l'email
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Cher ").append(userName).append(",\n\n");
        emailContent.append("Nous vous confirmons le paiement effectué le ").append(paymentDate);
        emailContent.append(" à ").append(paymentTime).append(". Votre achat comprend les articles suivants :\n\n");

        float totalPrice = 0; // Initialisation du prix total

        // Boucle pour ajouter les détails de chaque produit dans l'email
        for (Produit produit : panier) {
            String productName = produit.getNom();
            int productQuantity = produit.getStock();
            float productPrice = produit.getPrix();
            float productGroupPrice = productQuantity > 1 ? (productQuantity * productPrice) : productPrice;

            emailContent.append(productName).append(", x").append(productQuantity);
            emailContent.append(" - ").append(productPrice).append(" ").append(currency).append(" l'unité");
            emailContent.append(" - ").append(productGroupPrice).append(" ").append(currency).append(" le groupe\n");

            totalPrice += productGroupPrice;
        }

        emailContent.append("\nPrix total : ").append(totalPrice).append(" ").append(currency).append(".\n\n");
        emailContent.append("Merci pour votre achat chez nous.\n\n");
        emailContent.append("Cordialement,\nL'équipe de ").append(COMPANY_NAME);

        // Envoi de l'email via Courier
        SendEnhancedRequestBody request = new SendEnhancedRequestBody();
        SendRequestMessage message = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<>();
        to.put("email", user.getMail()); // Utilisation de l'adresse e-mail du destinataire
        message.setTo(to);

        HashMap<String, Object> content = new HashMap<>();
        content.put("title", "Confirmation de paiement");
        content.put("body", emailContent.toString()); // Utilisation du contenu préparé pour l'email
        message.setContent(content);

        request.setMessage(message);
        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(request);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs liées à l'envoi de l'email
        }
    }

}