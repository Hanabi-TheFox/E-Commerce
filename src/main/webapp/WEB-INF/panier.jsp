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
        body {
            margin: 0;
        }
        .payment-form {
            display: none;
        }
         .hidden {
             display: none;
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
        <th>Action </th>
    </tr>

    <%
        List<Produit> panier = Controller.getInstanceController().requestGetPanier();
        float montantTotal = Controller.getInstanceController().requestGetCommande().getPrix();
        if (panier != null) {
            for (Produit produit : panier) {
    %>

    <tr>
        <form action="ServletPanier" method="get">
            <td><%=produit.getNom()%> <br> <img src="imagesProduct/<%=produit.getIdProduit()%>.jpeg" alt="
                            <%=produit.getNom()%>" width="100"></td>
            <td><%=produit.getPrix()%></td>
            <td><input type="number" name="produitQuantite" min="1" value="<%=produit.getStock()%>"></td>
            <td><%=produit.getStock()%></td>
            <td><%=produit.getPrix() * produit.getStock()%></td>
            <td>
                <input type="hidden" name="produitId" value="<%=produit.getIdProduit()%>">
                <button type="submit" name="action" value="supprimer">Supprimer</button>
                <button type="submit" name="action" value="modifier">Modifier</button>
            </td>
        </form>
    </tr>

    <%
            }
        }
    %>
</table>

<p>Montant total des produits : <%=montantTotal%></p>

<form action="ServletPanier" method="post">
    <button type="submit" name="action" value="vider">Vider le panier</button>
    <button type="submit" name="action" value="payer">Payer</button>
</form>
</body>
</html>
