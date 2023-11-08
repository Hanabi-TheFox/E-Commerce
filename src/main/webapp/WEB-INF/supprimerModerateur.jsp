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
<h1>Modification des droits</h1>
<form action="ServletDeleteModerateur.jsp" method="post">
    <label for="email">Adresse email :</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="droits">Droits :</label>
    <select id="droits" name="droits" multiple>
        <option value="000">000</option>
        <option value="001">001</option>
        <option value="010">010</option>
        <option value="100">100</option>
        <option value="110">110</option>
        <option value="101">101</option>
        <option value="011">011</option>
        <option value="111">111</option>
    </select><br><br>

    <input type="submit" value="Modifier">
</form>
</body>
</html>
