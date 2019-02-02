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
    <script src="../../../../../js/game-table-app.js"></script>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
    <link rel="stylesheet" href="../../../../../css/style.css">
    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">-->
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">-->

    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">-->
</head>
<body>

<div id="container">
    <div id="game-table_container">

        <%--nagłówek--%>
        <div class="game-table_header">
            <div>Nazwa stołu: ${gameTable}</div>
            <div id="nickname"></div>
        </div>

        <%--gracze--%>
        <div class="game-table_players">
            <div class="player_container">
                <div class="red-team-color"></div>
                <div class="player-name_container" data-seat-name="redTeamSeat1">${redTeamSeat1}</div>
            </div>
            <div class="gap" style="display: flex; align-items: center;">
                <div class="clue-word">Aktualna wskazówka:</div>
                <div  class="clue-word" id="clue-word"></div>
            </div>
            <div class="player_container">
                <div class="blue-team-color"></div>
                <div class="player-name_container" data-seat-name="blueTeamSeat1">${blueTeamSeat1}</div>
            </div>
        </div>

        <%--gra--%>
        <div class="game-table_game">
            <div class="gamewords-row">
                <button class="gameword"><span>słowo</span></button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
            </div>
            <div class="gamewords-row">
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
            </div>
            <div class="gamewords-row">
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
            </div>
            <div class="gamewords-row">
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
            </div>
            <div class="gamewords-row">
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
                <button class="gameword">słowo</button>
            </div>

        </div>

        <%--gracze--%>
        <div class="game-table_players">
            <div class="player_container">
                <div class="red-team-color"></div>
                <div class="player-name_container" data-seat-name="redTeamSeat2">${redTeamSeat2}</div>
            </div>
            <div class="gap">
                <form action="" method="post" id="clue-word-form">
                    <%--<label>Wskazówka:--%>
                        <input type="text" name="clueWord" placeholder="Wprowadź wskazówkę...">
                    <%--</label>--%>

                    <button type="submit">Wyślij</button>
                    <%--<input type="submit" value = "send">--%>
                </form>

            </div>
            <div class="player_container">
                <div class="blue-team-color"></div>
                <div class="player-name_container" data-seat-name="blueTeamSeat2">${blueTeamSeat2}</div>
            </div>
        </div>

    </div>
</div>


</body>
</html>
