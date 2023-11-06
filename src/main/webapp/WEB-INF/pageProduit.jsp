<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entity.Produit" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des Produits</title>
</head>
<body>
<h1>Liste des Produits</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prix</th>
        <th>Description</th>
        <th>Stock</th>
        <th>Image</th>
    </tr>
    <c:forEach items="${listeProduits}" var="produit">
        <tr>
            <td>${produit.getIdProduit()}</td>
            <td>${produit.getNom()}</td>
            <td>${produit.getPrix()}</td>
            <td>${produit.getDescription()}</td>
            <td>${produit.getStock()}</td>
            <td><img src="ecommerce/ecommerce/model/imagesProduct/${produit.getIdProduit()}.jpeg" alt="${produit.nom}" width="100"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
