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

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #333;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        img {
            max-width: 100px;
            height: auto;
        }
    </style>
</head>
<body>
<h1>Liste des Produits</h1>
<table>
    <tr>
        <th>ID Produit</th>
        <th>Nom</th>
        <th>Prix</th>
        <th>Description</th>
        <th>Image</th>
    </tr>
    <%
        List<Produit> listeProduits = (List<Produit>) request.getAttribute("listeProduits");
        for (Produit produit : listeProduits) {
    %>
    <tr>
        <td><%= produit.getIdProduit() %></td>
        <td><%= produit.getNom() %></td>
        <td><%= produit.getPrix() %></td>
        <td>
            <img src="imagesProduct/1.jpeg" alt="<%= produit.getNom() %>" width="100">
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
