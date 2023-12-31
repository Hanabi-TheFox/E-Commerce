<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
        }

        h1 {
            color: #333;
        }

        form {
            background-color: #fff;
            max-width: 400px;
            margin: 0 auto 50px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            text-align: left;
            margin: 10px 0;
        }

        input[type="text"],
        input[type="password"] {
            width: 80%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 20px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        p {
            margin-top: 15px;
        }
    </style>
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

        function validerMail(champ) {
            var value = champ.value;
            // Utiliser une expression régulière pour vérifier le format de l'adresse e-mail
            var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (!emailPattern.test(value)) {
                alert("Le champ " + champ.name + " ne contient pas une adresse e-mail valide.");
                // Réinitialiser la valeur du champ à une chaîne vide
                champ.value = '';
            }
        }

        function validerMotDePasse(champ) {
            validerChamp(champ, /[^A-Za-z0-9?!*_/\\-]/g);
        }
    </script>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Inscription</h1>
<form action="ServletDInscription" method="post">
    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required onblur="validerNomPrenom(this)">
    <br>
    <label for="prenom">Prénom :</label>
    <input type="text" id="prenom" name="prenom" required onblur="validerNomPrenom(this)">
    <br>
    <label for="email">Mail :</label>
    <input type="text" id="email" name="email" required onblur="validerMail(this)">
    <br>
    <label for="motDePasse">Mot de passe :</label>
    <input type="password" id="motDePasse" name="motDePasse" required onblur="validerMotDePasse(this)">
    <br>
    <input type="submit" value="S'inscrire" >
</form>
<div class="footer">
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
