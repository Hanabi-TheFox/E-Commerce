<%@ page import="entity.Utilisateur" %>
<%@ page import="jdk.jshell.execution.Util" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil de l'Utilisateur</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        h1 {
            color: #333;
        }

        h2 {
            color: #555;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin: 10px 0;
        }

        strong {
            color: #333;
        }

        a {
            text-decoration: none;
            color: #0077cc;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Profil de l'Utilisateur</h1>
    <p style="color: #0077cc;">Bienvenue, ${prenom} !</p>

    <h2>Informations de Profil :</h2>
    <ul>
        <li><strong>Nom :</strong> ${nom}</li>
        <li><strong>Prenom :</strong> ${prenom}</li>
        <li><strong>Email :</strong> ${email}</li>
        <!-- Ajoutez d'autres informations de profil ici -->
    </ul>

    <p><a href="ServletDeDeconnexion">Déconnexion</a></p> <!-- Lien pour se déconnecter -->
</div>
</body>
</html>
