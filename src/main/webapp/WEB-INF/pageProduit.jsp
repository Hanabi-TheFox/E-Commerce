<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entity.Produit" %>

<%
    Produit produit = (Produit) request.getAttribute("produit");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page Produit</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
            margin: 10px 0;
        }

        h1 {
            text-align: center;
            background-color: #333;
            color: #fff;
            padding: 10px;
        }

        .container {
            display: flex;
            justify-content: center;
        }

        .product-details {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
            text-align: center;
        }

        .product-image {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Détails du Produit</h1>
<div class="container">
    <div class="product-details">
        <img src="imagesProduct/<%= produit.getIdProduit() %>.jpeg" class="product-image" alt="<%= produit.getNom() %>">
        <ul>
            <li><strong>Nom du Produit:</strong> <%= produit.getNom() %></li>
            <li><strong>Description:</strong> <%= produit.getDescription() %></li>
            <li><strong>Prix:</strong> <%= produit.getPrix() %> €</li>
            <li><strong>Stock Disponible:</strong> <%= produit.getStock() %> unités</li>
        </ul>
    </div>
</div>
</body>
</html>
