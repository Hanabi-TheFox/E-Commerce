<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil de l'Utilisateur</title>
</head>
<body>
<%

%>

<h1>Profil Moderateur</h1>
<p>Bienvenue, ${prenom} !</p>

<h2>Informations de Profil :</h2>
<ul>
    <li><strong>Nom :</strong> ${nom}</li>
    <li><strong>Prenom :</strong> ${prenom}</li>
    <li><strong>Email :</strong> ${email}</li>
    <li><strong>Droits :</strong> ${droits}</li>
    <!-- Ajoutez d'autres informations de profil ici -->
</ul>

<p><a href="ServletDeDeconnexion">Deconnexion</a></p> <!-- Lien pour se déconnecter -->
</body>
</html>