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
        }

        h1 {
            color: #333;
        }

        form {
            background-color: #fff;
            margin: 0 auto;
            padding: 20px;
            width: 60%;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            text-align: center;
            margin-bottom: 10px;
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
    </script>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Suppression moderateurs</h1>
<form action="ServletDeleteModerateur" method="post" class="supp">
    <label for="email">Veuillez renseigner l'Adresse email du moderateur que vous voulez supprimer :</label>
    <input type="email" id="email" name="email" required><br><br>

    <input type="submit" value="Supprimer">
</form>
<%
    Boolean suppression = (Boolean) request.getAttribute("suppression");
    if (suppression != null && suppression) {
%>
<p class="success">Le moderateur a ete supprime</p>
<%
    }
%>
</body>
</html>
