<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.String" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page de Connexion</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        h1 {
            color: #333;
            text-align: center;
            padding: 20px;
        }
        .container {
            max-width: 400px;
            margin: 0 auto;
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
            width: 100%;
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
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #555;
        }
        p.error {
            color: red;
            text-align: center;
            margin-top: 10px;
            display: <%=(errorMessage != null && !errorMessage.isEmpty()) ? "block" : "none"%>;
        }
        a {
            color: #333;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>Connexion</h1>
<div class="container">
    <form action="ServletDeConnexion" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>
        <label for="motDePasse">Mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required>
        <input type="submit" value="Se connecter">
    </form>
    <p class="error"><%= errorMessage %></p>
</div>
<p style="text-align: center;">Pas encore de compte ? <a href="ServletDInscription">Inscrivez-vous ici</a></p>
</body>
</html>
