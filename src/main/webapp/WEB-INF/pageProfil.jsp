<%@ page import="entity.Utilisateur" %>
<%@ page import="jdk.jshell.execution.Util" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="ecommerce.ecommerce.model.DAO.UtilisateurDAO" %>
<%@ page import="entity.Client" %>
<%@ page import="entity.Moderateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil de l'Utilisateur</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto 100px;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        h1 {
            color: #333;
        }

        h2 {
            color: #555;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin: 10px 0;
        }

        strong {
            color: #333;
        }

        a {
            text-decoration: none;
            color: #0077cc;
        }

        button {
            background-color: #0077cc;
            color: #f0f0f0;
            width: 25%;
            height: 8%;
            box-sizing: border-box;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s ease; /* Ajout d'une transition pour une animation plus fluide */
        }

        button:hover {
            background-color: #333;
            transform: scale(1.05); /* Effet de zoom au survol */
        }

        .left {
            margin-left: 8%;
        }

        .footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px 0;
            position: relative;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<div style="margin-bottom: 50px;"></div>
<div class="container">
    <%
        Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
        String typeCompte = u.getTypeDeCompte();
    %>
    <h1>Profil <%= typeCompte %>
    </h1>
    <p style="color: #0077cc;">Bienvenue, <%= u.getPrenom() %> !</p>

    <h2> Informations de Profil </h2>
    <ul>
        <li><strong>Nom :</strong> <%= u.getNom() %>
        </li>
        <li><strong>Prenom :</strong> <%= u.getPrenom() %>
        </li>
        <li><strong>Email :</strong> <%= u.getMail() %>
        </li>
        <% if (typeCompte.equals("Client")) {
            Client c = UtilisateurDAO.findClientByUtilisateur(u);
            assert c != null;
            String carteBancaireNum = c.getCompteBancaireNum();
            if (!"0000 0000 0000 0000".equals(carteBancaireNum)) {
                // Affiche uniquement les 4 derniers chiffres
                String lastFourDigits = carteBancaireNum.substring(carteBancaireNum.length() - 4);
        %>
        <li><strong>Carte Bancaire :</strong> **** **** **** <%= lastFourDigits %>
        </li>
        <%
            }
        %>
        <li><strong>Solde :</strong> <%= c.getCompteBancaireSolde() %>
        </li>
        <form action="ServletAjouterSolde" method="get">
            <button type="submit">Ajouter Solde</button>
        </form>
        <li><strong>Points de fidélité :</strong> <%= c.getPoints() %>
        </li>
        <form action="ServletConvertPoints" method="get">
            <button type="submit">Convertir les points</button>
        </form>
        <%
        } else if (typeCompte.equals("Moderateur")) {
            Moderateur m = UtilisateurDAO.findModByUtilisateur(u);
            String droits = getRightsPrint(m);
        %>
        <li><strong>Droits :</strong>
            <div class="left"><%= droits %>
            </div>
        </li>
        <%
            }
        %>
    </ul>
    <%
        if (typeCompte.equals("Client")) {
            Client c = UtilisateurDAO.findClientByUtilisateur(u);
            assert c != null;
            String carteBancaireNum = c.getCompteBancaireNum();
            String buttonText = "Ajouter";
            if (!"0000 0000 0000 0000".equals(carteBancaireNum)) {
                buttonText = "Modifier";
            }
    %>
    <p><a href="ServletAjouterMoyenPaiement">
        <button><%= buttonText %> carte bancaire</button>
    </a></p>
    <%
        }
    %>
    <p><a href="ServletModifierProfil">Modifier le profil</a></p>
    <p><a href="ServletChangerMotDePasse">Changer mot de passe</a></p>
    <p><a href="ServletDeDeconnexion">Deconnexion</a></p> <!-- Lien pour se déconnecter -->

</div>
<div class="footer">
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
<%! private static String getRightsPrint(Moderateur m) {
    String droits = "";
    if (m.getDroits().equals("000")) {
        droits = "- Aucun droit";
    } else {
        if (m.getDroits().charAt(0) == '1') {
            droits += "- Ajouter un produit" + "<br>";
        }
        if (m.getDroits().charAt(1) == '1') {
            droits += "- Supprimer un produit" + "<br>";
        }
        if (m.getDroits().charAt(2) == '1') {
            droits += "- Ajouter un modérateur" + "<br>";
        }
    }
    return droits;
}
%>
