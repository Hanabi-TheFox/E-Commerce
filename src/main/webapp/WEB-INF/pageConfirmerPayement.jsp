<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.naming.ldap.Control" %>
<%@ page import="entity.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Solde</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    .header {
        background-color: #333;
        color: #fff;
        padding: 10px;
        text-align: center;
    }
</style>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Confirmer le payement</h1>

<form action="ServletPayerCommande" method="post">
    <label for="numeroCarte">Numéro de carte :</label>
    <input type="text" id="numeroCarte" name="numeroCarte" required>
    <input type="submit" value="Payer">
</form>
</body>
</html>
