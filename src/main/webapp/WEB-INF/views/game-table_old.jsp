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
    <script src="../../../../../js/app_2.js"></script>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="../../../../../css/style.css">
    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">-->
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">-->

    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">-->
</head>
<body>

<div id="container">
    <div><h3>${gameTable}</h3></div>
    <div id="game-table_container">
        <div class="game-table_header">header</div>
        <div class="game-table_players">
            <div class="player_container">
                <div class="red-team-color"></div>
                <div class="player-name_container">
                    <input type="button" id="seat-down" value="wolne miejsce" data-seat-id="1">
                    <div class="player-name hide"></div>
                    <div class="player-exit hide">
                        <input type="button" id="exit-seat" value="ex">
                    </div>

                </div>
            </div>
            <div class="gap"></div>
            <div class="player_container">
                <div class="blue-team-color"></div>
                <div class="player-name_container">
                    <div class="player-name">puste miejsce</div>
                    <div class="player-exit">exit</div>
                </div>
            </div>
        </div>
        <div class="game-table_game">game</div>
        <div class="game-table_players">
            <div class="player_container">
                <div class="red-team-color"></div>
                <div class="player-name_container">
                    <div class="player-name">puste miejsce</div>
                    <div class="player-exit">exit</div>
                </div>
            </div>
            <div class="gap"></div>
            <div class="player_container">
                <div class="blue-team-color"></div>
                <div class="player-name_container">
                    <div class="player-name">puste miejsce</div>
                    <div class="player-exit">exit</div>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
