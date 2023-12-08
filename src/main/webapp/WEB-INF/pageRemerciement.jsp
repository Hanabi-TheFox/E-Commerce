<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Remerciement pour votre achat</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- Animate.css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">

    <!-- Favicon -->
    <link rel="icon" href="logo/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="logo/logo.png" type="image/x-icon">
</head>
<body>

<div class="container text-center mt-5">
    <h1 class="animate__animated animate__fadeInDown">Merci pour votre achat!</h1>
    <p class="lead animate__animated animate__fadeInUp">Nous apprécions votre confiance en nos produits.</p>

    <!-- Animation avec le logo de l'entreprise -->
    <div class="animate__animated animate__bounceIn">
        <!-- Utilisation du chemin spécifié pour le logo -->
        <img src="logo/logo.png" alt="Logo de l'entreprise" class="img-fluid" width="100">
    </div>

    <p class="mt-3 animate__animated animate__fadeIn">Votre satisfaction est notre priorité.</p>
    <a href="ServletProduits" class="btn btn-primary mt-3 animate__animated animate__fadeIn">Retour à l'accueil</a>
</div>

<!-- Bootstrap JS (facultatif, mais nécessaire si vous utilisez des fonctionnalités Bootstrap JavaScript) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<div class="footer">
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
