<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<header>
    <nav class="navbar">
        <ul>
            <li><a href="#">Accueil</a></li>
            <li><a href="#">Produits</a></li>
            <li><a href="#">À propos de nous</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
    </nav>
</header>

<div id="footer-content">
    <!-- Contenu du footer spécifique à cette page -->
    <p>&copy; 2023 Votre Entreprise. Tous droits réservés.</p>
</div>
</body>
<style>
    /* Styles pour la barre de navigation */
    .navbar {
        background-color: #333;
        padding: 10px 0;
    }

    .navbar ul {
        list-style-type: none;
        padding: 0;
        text-align: center;
    }

    .navbar li {
        display: inline;
        margin: 0 20px;
    }

    .navbar a {
        text-decoration: none;
        color: #fff;
        font-weight: bold;
        font-size: 16px;
        transition: color 0.3s;
    }

    .navbar a:hover {
        color: #ff8c00;
    }

    /* Styles pour le contenu du footer */
    #footer-content {
        text-align: center;
        background-color: #333;
        color: #fff;
        padding: 20px 0;
    }
</style>
</html>
