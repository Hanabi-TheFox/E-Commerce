<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 23/10/23
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Page de Connexion</title>
</head>
<body>
<h1>Connexion</h1>
<form action="ServletDeConnexion" method="post">
    <label for="identifiant">Identifiant :</label>
    <input type="text" id="identifiant" name="identifiant" required>
    <br>
    <label for="motDePasse">Mot de passe :</label>
    <input type="password" id="motDePasse" name="motDePasse" required>
    <br>
    <input type="submit" value="Se connecter">
</form>
<p>Pas encore de compte ? <a href="pageInscription.jsp">Inscrivez-vous ici</a></p>
</body>
</html>
