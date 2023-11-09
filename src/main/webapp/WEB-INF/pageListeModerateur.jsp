<%@ page import="entity.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="ecommerce.ecommerce.controller.Controller" %>
<%@ page import="entity.Moderateur" %>
<%@ page import="ecommerce.ecommerce.model.DAO.UtilisateurDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Modérateurs</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        /* Styles CSS pour la mise en page */
        body {
            font-family: Arial, sans-serif;
        }
        .moderators {
            width: 80%;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px 12px;
            text-align: left;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        .button-container button {
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<div class="moderators">
    <h1>Liste des Modérateurs</h1>
<%--    <% String message = (String) request.getAttribute("Ajouter");
        if (message != null){
    %>
            <h4> ${message} </h4>
    <%
            request.setAttribute("Ajouter", "");
        }
    %>--%>
    <table>
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Mail</th>
            <th>Droits</th>
        </tr>
        <%
            Utilisateur u = Controller.getInstanceController().requestGetUtilisateur();
            List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("listModerateur");
            for (Utilisateur utilisateur : utilisateurs) {
                Moderateur moderateur = UtilisateurDAO.findModByUtilisateur(utilisateur);
        %>
        <tr>
            <td><%= utilisateur.getPrenom() %></td>
            <td><%= utilisateur.getNom() %></td>
            <td><%= utilisateur.getMail() %></td>
            <td><%= moderateur.getDroits() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <div class="button-container">
        <%
            if (u.getTypeDeCompte().equals("Admin")) {
        %>
                <form action="ServletAddModerateur" method ="get">
                    <button type="submit">Ajouter Modérateur</button>
                </form>
        <%
            }
            if (u.getTypeDeCompte().equals("Moderateur")) {
                Moderateur m = UtilisateurDAO.findModByUtilisateur(u);
                if (m.getDroits().charAt(2) == '1'){
        %>
                    <form action="ServletAddModerateur" method ="get">
                        <button type="submit">Ajouter Modérateur</button>
                    </form>
        <%
                }
            }
        %>
            <%
                if (u.getTypeDeCompte().equals("Admin")) {
            %>
                    <form action="ServletDeleteModerateur" method ="get">
                        <button type="submit">Supprimer Modérateur</button>
                    </form>
                    <form action="ServletModifyRights" method ="get">
                        <button type="submit">Modifier les droits</button>
                    </form>
            <%
                }
            %>
    </div>
</div>
</body>
</html>

