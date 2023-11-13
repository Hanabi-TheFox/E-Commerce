<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <%
        Boolean modification = (Boolean) request.getAttribute("modification");
    %>
    <meta charset="UTF-8">
    <title>Modifier droit modérateur</title>
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin : 0;
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

        input[type="email"],
        select {
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

        .success {
            color: green;
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="header.jsp" %>
</div>
<h1>Modification des droits</h1>
<form action="ServletModifyRights" method="post">
    <label for="email">Adresse email :</label>
    <input type="email" id="email" name="email" required><br><br>
    <label for="droits">Droits :</label>
    <select id="droits" name="droits">
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

    <%
        if (modification != null && modification) {
    %>
    <p class="success">Le modérateur a été mis à jour</p>
    <%
        }
    %>
</form>
</body>
</html>
