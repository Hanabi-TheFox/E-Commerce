        <%@ page import="entity.Utilisateur" %>
<%@ page import="jdk.jshell.execution.Util" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="ecommerce.ecommerce.model.DAO.UtilisateurDAO" %>
<%@ page import="entity.Client" %>
        <%@ page import="entity.Moderateur" %>
        <!DOCTYPE html>
<html>
<head>
    <title>Profil de l'Utilisateur</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
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
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<div class="container">
    <%
        Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
        if (u.getTypeDeCompte().equals("Client")) {
            Client c = UtilisateurDAO.findClientByUtilisateur(u);
    %>
        <h1>Profil Client</h1>
        <p style="color: #0077cc;">Bienvenue, <%=u.getPrenom()%> !</p>

        <h2>Informations de Profil :</h2>
        <ul>
            <li><strong>Nom :</strong> <%=u.getNom()%> </li>
            <li><strong>Prenom :</strong> <%=u.getPrenom()%> </li>
            <li><strong>Email :</strong> <%=u.getMail()%> </li>
            <li><strong>Solde :</strong> <%=c.getCompteBancaireSolde()%> </li>
            <li><strong>Points de fidelite :</strong> <%=c.getPoints()%> </li>
        </ul>
    <%
        }else if (u.getTypeDeCompte().equals("Moderateur")){
            Moderateur m = UtilisateurDAO.findModByUtilisateur(u);
    %>
        <h1>Profil Moderateur</h1>
        <p style="color: #0077cc;">Bienvenue, <%=u.getPrenom()%> !</p>

        <h2>Informations de Profil :</h2>
        <ul>
            <li><strong>Nom :</strong> <%=u.getNom()%> </li>
            <li><strong>Prenom :</strong> <%=u.getPrenom()%> </li>
            <li><strong>Email :</strong> <%=u.getMail()%> </li>
            <li><strong>Droits :</strong> <%=m.getDroits()%></li>
        </ul>
    <%
        }else { // u.getTypeDeCompte.equals("Admin")
    %>
        <h1>Profil Admin</h1>
        <p style="color: #0077cc;">Bienvenue, <%=u.getPrenom()%> !</p>

        <h2>Informations de Profil :</h2>
        <ul>
            <li><strong>Nom :</strong> <%=u.getNom()%> </li>
            <li><strong>Prenom :</strong> <%=u.getPrenom()%> </li>
            <li><strong>Email :</strong> <%=u.getMail()%> </li>
        </ul>
    <%
        }
    %>
    <p><a href="ServletDeDeconnexion">Deconnexion</a></p> <!-- Lien pour se déconnecter -->

</div>
</body>
</html>
