<%@ page import="javax.naming.ldap.Control" %>
<%@ page import="entity.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Solde</title>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Ajouter un Solde</h1>
<%
    Utilisateur utilisateur = Controller.getInstanceController().requestGetUtilisateur();
    Client client = UtilisateurDAO.findClientByUtilisateur(utilisateur);
    %>
    <p>Votre solde actuel est de : <%= client.getCompteBancaireSolde() %></p>

<form action="ServletAjouterSolde" method="post">
    <label for="numeroCarte">Numéro de Carte Bleue:</label>
    <input type="text" id="numeroCarte" name="numeroCarte" required><br><br>

    <label for="montantSolde">Montant du Solde:</label>
    <select id="montantSolde" name="montantSolde" required>
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="25">25</option>
        <option value="100">100</option>
    </select>
    <br><br>

    <label for="optionChoix">Option de Choix:</label>
    <input type="text" id="optionChoix" name="optionChoix" required><br><br>

    <!-- Autres champs du formulaire si nécessaire -->

    <!-- Bouton de soumission du formulaire -->
    <button type="submit">Ajouter Solde</button>
</form>
</body>
</html>
