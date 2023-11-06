<%@ page import="entity.Utilisateur" %>
<%@ page import="jdk.jshell.execution.Util" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil de l'Utilisateur</title>
</head>
<body>
<%

%>

<h1>Profil Client</h1>
<p>Bienvenue, ${prenom} !</p>

<h2>Informations de Profil :</h2>
<ul>
    <li><strong>Nom :</strong> ${nom}</li>
    <li><strong>Prenom :</strong> ${prenom}</li>
    <li><strong>Email :</strong> ${email}</li>
    <li><strong>Solde :</strong> ${solde}</li>
    <li><strong>Points de fidelite :</strong> ${points}</li>
    <!-- Ajoutez d'autres informations de profil ici -->
</ul>

<p><a href="ServletDeDeconnexion">Deconnexion</a></p> <!-- Lien pour se déconnecter -->
</body>
</html>