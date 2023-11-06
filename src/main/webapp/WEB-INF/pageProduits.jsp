<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entity.Produit" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des Produits</title>
</head>
<body>
<h1>Liste des Produits</h1>
<table>
    <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Mail</th>
    </tr>
    <%
        List<Produit> listeProduits = (List<Produit>) request.getAttribute("listModerateur");
        for (Produit produit : listeProduits) {
    %>
    <tr>
        <td><%= produit.getIdProduit() %></td>
        <td><%= produit.getNom() %></td>
        <td><%= produit.getPrix() %></td>
        <td><%= produit.getDescription() %></td>
        <td><img src="ecommerce/ecommerce/model/imagesProduct/<%=produit.getIdProduit()%>.jpeg" alt="<%=produit.getNom()%>" width="100"></td>

    </tr>
    <%
        }
    %>
</table>

</body>
</html>