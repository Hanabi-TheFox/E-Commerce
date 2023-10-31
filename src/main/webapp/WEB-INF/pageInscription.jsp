<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        form {
            background-color: #fff;
            max-width: 400px;
            margin: 0 auto;
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
            width: 100%;
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
