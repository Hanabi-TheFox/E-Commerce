<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 23/10/23
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription</title>
    <script>
        function validerChamp(champ, pattern) {
            var value = champ.value;
            var newValue = value.replace(pattern, '');
            if (newValue !== value) {
                alert("Le champ " + champ.name + " contient des caractères non valides. Ils ont été supprimés.");
                champ.value = newValue;
            }
        }

        function validerNomPrenom(champ) {
            validerChamp(champ, /[^A-Za-z\- ]/g);
        }

        function validerIdentifiantMotDePasse(champ) {
            validerChamp(champ, /[^A-Za-z0-9?!*_/\\-]/g);
        }
    </script>
</head>
<body>
<h1>Inscription</h1>
<form action="ServletDInscription" method="post">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required onblur="validerNomPrenom(this)">
    <br>
    <label for="prenom">Prénom :</label>
    <input type="text" id="prenom" name="prenom" required onblur="validerNomPrenom(this)">
    <br>
    <label for="identifiant">Identifiant :</label>
    <input type="text" id="identifiant" name="identifiant" required onblur="validerIdentifiantMotDePasse(this)">
    <br>
    <label for="motDePasse">Mot de passe :</label>
    <input type="password" id="motDePasse" name="motDePasse" required onblur="validerIdentifiantMotDePasse(this)">
    <br>
    <input type="submit" value="S'inscrire">
</form>
</body>
</html>