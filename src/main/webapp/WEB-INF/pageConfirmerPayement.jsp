<%@ page import="javax.naming.ldap.Control" %>
<%@ page import="entity.Client" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmer le Paiement</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
            width: 100%;
        }
        .title {
            color: #333;
            text-align: center;
            padding: 20px;
        }
        .content {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
            max-width: 400px;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        label {
            font-weight: bold;
            display: block;
        }
        input[type="text"],
        input[type="password"] {
            width: 80%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 80%;
        }
        input[type="submit"]:hover {
            background-color: #555;
        }
        a {
            color: #333;
            text-decoration: none;
        }
        .footer {
            text-align: center;
            background-color: #333;
            color: #fff;
            padding: 10px 0;
        }
    </style>
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<div class="title">
    <h1>Confirmer le Paiement</h1>
</div>
<% if(errorMessage != null){ %>
<p class="error" style="color : red;text-align : center"><%= errorMessage %></p>
<% } %>
<div class="content">
    <form action="ServletPayerCommande" method="post">
        <label for="numeroCarte">Numéro de carte :</label>
        <input type="text" id="numeroCarte" name="numeroCarte" required>
        <input type="submit" value="Payer">
    </form>
</div>
</body>
</html>
