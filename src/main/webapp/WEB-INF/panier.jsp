        <%@ page import="entity.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body{
            margin: 0;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Panier</h1>

<table border="1">
    <tr>
        <th>Produit</th>
        <th>Prix unitaire</th>
        <th>Quantité</th>
        <th>Prix total</th>
    </tr>


    <%
        List<Produit> panier = Controller.getInstanceController().requestGetPanier();
        float montantTotal = Controller.getInstanceController().requestGetCommande().getPrix();
        if (panier != null) {
            for (Produit produit : panier) {
    %>
    <tr>
        <td><%=produit.getNom()%> <br> <img src="imagesProduct/<%=produit.getIdProduit()%>.jpeg" alt="
                        <%=produit.getNom()%>" width="100"></td>
        <td><%=produit.getPrix()%></td>
        <td><%=produit.getStock()%></td>
        <td><%=produit.getPrix() * produit.getStock()%></td>
    </tr>
    <%
            }
        }
    %>
</table>


<p>Montant total des produits : <%=montantTotal%></p>

<form action="ServletPanier" method="post">
    <input type="hidden" name="action" value="vider">
    <input type="submit" value="Tout effacer">
</form>
</body>
</html>
