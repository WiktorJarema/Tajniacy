<%--
  Created by IntelliJ IDEA.
  User: wiktor
  Date: 25.01.19
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Tajniacy</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../../../../../js/app.js"></script>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="../../../../../css/style.css">
    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">-->
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">-->

    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">-->
</head>
<body>

<div id="container">
    <div class="online-users">
        <span>Aktualna liczba użytkowników online:&nbsp</span>
        <span id="number-online-users">0</span>
    </div>
    <div class="width_container">
        <div id="index_header">
            <div class="welcome">${welcomeMessage1}</div>
            <div class="welcome">${welcomeMessage2}</div>
            <div class="welcome">${welcomeMessage3}<strong>&nbsp;${nickname_name}</strong></div>
        </div>
        <h4>Lista stołów:</h4>
        <div id="list_container"></div>
        <div id="footer">
            <div class="info">
                <div class="welcome">${infoMessage1}</div>
                <div class="welcome">${infoMessage2}</div>
            </div>
            <div class="cookies">${cookiesMessage}</div>
        </div>
    </div>

</div>

</body>
</html>
