<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="entity.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Produits</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            padding: 10px;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        .produit {
            border: 1px solid #ddd;
            margin: 10px;
            padding: 10px;
            width: 250px;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .produit img {
            max-width: 100%;
            height: auto;
            cursor: pointer;
        }

        .produit h2 {
            font-size: 20px;
            margin: 10px 0;
        }

        .produit p {
            font-size: 16px;
            color: #777;
        }

        .footer {
            text-align: center;
            background-color: #333;
            color: #fff;
            padding: 10px 0;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Liste des Produits</h1>
<div class="container">
    <%
        List<Produit> listeProduits = (List<Produit>) request.getAttribute("listeProduits");
        Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
        Client client = (Client) request.getAttribute("client");

        for (Produit produit : listeProduits) {
    %>
    <div class="produit">
        <a href="ServletProduit?id=<%= produit.getIdProduit() %>">
            <img src="imagesProduct/<%= produit.getIdProduit() %>.jpeg" alt="<%= produit.getNom() %>" width="100">
        </a>
        <h2><%= produit.getNom() %></h2>
        <p>Prix : <%= produit.getPrix() %> €</p>
        <!-- Formulaire pour ajouter au panier -->
        <form action="ServletPanier" method="get">
            <input type="hidden" name="action" value="ajouter">
            <input type="hidden" name="produitId" value="<%= produit.getIdProduit() %>">
            <input type="hidden" name="produitNom" value="<%= produit.getNom() %>">
            <input type="hidden" name="produitPrix" value="<%= produit.getPrix() %>">
            <input type="hidden" name="produitQuantite" value="1">
            <% if (client != null) { %>
            <input type="submit" value="Ajouter au panier">
            <% } %>
        </form>
    </div>

    <%
        }
    %>
</div>
<div class="footer">
    <p>&copy; 2023 Votre Entreprise. Tous droits réservés.</p>
</div>
</body>
</html>
