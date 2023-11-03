<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 03/11/2023
  Time: 09:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Informations sur le produit</title>
</head>
<body>
<h1>Informations sur le produit</h1>
<%  String nom = request.getParameter("produitNom");
    double prix = Double.parseDouble(request.getParameter("produitPrix"));
    int stock = Integer.parseInt(request.getParameter("produitStock"));
    String description = request.getParameter("produitDescription");

        // Afficher les informations du produit
        out.println("<p>Nom : " + nom + "</p>");
        out.println("<p>Prix : " + prix + "</p>");
        out.println("<p>Stock : " + stock + "</p>");
        out.println("<p>Description : " + description + "</p>");
%>
</body>
</html>
