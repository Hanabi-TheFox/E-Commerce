<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Produit</title>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Ajouter un Produit</h1>

<form action="ServletAjouterProduit" method="post" enctype="multipart/form-data">
    <label for="nom">Nom du Produit:</label>
    <input type="text" id="nom" name="nom" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>

    <label for="prix">Prix:</label>
    <input type="text" id="prix" name="prix" required><br><br>

    <label for="stock">Stock:</label>
    <input type="text" id="stock" name="stock" pattern="[0-9]+" required><br><br>

    <!-- Vous pouvez ajouter un champ pour télécharger une image ici si nécessaire -->
    <label for="image">Image du Produit:</label>
    <input type="file" id="image" name="image" accept="image/*" required>
    <br>

    <input type="submit" value="Ajouter">
</form>
</body>
</html>
