<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entity.Produit" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Produits</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            background-color: #333;
            color: #fff;
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
        }

        .produit h2 {
            font-size: 20px;
            margin: 10px 0;
        }

        .produit p {
            font-size: 16px;
            color: #777;
        }
    </style>
</head>
<body>
<h1>Liste des Produits</h1>
<div class="container">
    <%
        List<Produit> listeProduits = (List<Produit>) request.getAttribute("listeProduits");
        for (Produit produit : listeProduits) {
    %>
    <div class="produit">
        <img src="imagesProduct/<%=produit.getIdProduit()%>.jpeg" alt="<%= produit.getNom() %>">
        <h2><%= produit.getNom() %></h2>
        <p>Prix : <%= produit.getPrix() %> €</p>
        <p><%= produit.getDescription() %></p>
    </div>
    <%
        }
    %>
</div>
<a href="ServletProfil">Profil</a>
</body>
</html>
