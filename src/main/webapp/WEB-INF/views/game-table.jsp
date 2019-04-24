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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">-->
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">-->

    <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">-->
</head>
<body>

<div id="container">
    <a href="/">Strona główna</a>
    <div id="game-table_container">

        <%--nagłówek--%>
        <div id="game-table_header">
            <div style="width: 860px">

                <div>Nazwa stołu: ${gameTable}</div>
                <div id="nickname"></div>
            </div>
            <button id="newGameWords" style="width: 100px">Nowa gra</button>
        </div>

        <%--gracze--%>
        <div class="game-table_players">
            <div class="player_container">
                <div id="redTeamSeat1Icon" class="icon-red"><img src="../../img/spymaster_icon3.png" class="img-icon-red"></div>
                <button id="redTeamSeat1TakeASeat" data-seat-id="1" data-seat-action="take" class="seat-red">wolne miejsce</button>
                <div <%--id="partnerSeat"--%> id="redTeamSeat1" class="player-name hidden">test</div>
                <button id="redTeamSeat1LeaveASeat" data-seat-id="1" data-seat-action="leave" class="seat-leave hidden"><i class="fa fa-close"></i></button>
                <%--<span class="dot red-dot"></span>--%>

                <%--<name>tymczasowy</name>--%>
                <%--<div class="red-team-color"></div>--%>
                <%--<div class="player-name_container" data-seat-name="redTeamSeat1"><i>::before</i>${redTeamSeat1}</div>--%>
            </div>
            <div class="gap" style="display: flex; align-items: center;">
                <div class="clue-word">Aktualna wskazówka:</div>
                <div  class="clue-word" id="clue-word"></div>
            </div>
            <div class="player_container">
                <%--<span class="dot blue-dot"></span>--%>
                <div id="blueTeamSeat1Icon" class="icon-blue"><img src="../../img/spymaster_icon3.png" class="img-icon-blue"></div>
                <button id="blueTeamSeat1TakeASeat" data-seat-id="3" data-seat-action="take" class="seat-blue">wolne miejsce</button>
                <div <%--id="opponentOtherRoleSeat"--%> id="blueTeamSeat1" class="player-name hidden">test</div>
                <button id="blueTeamSeat1LeaveASeat" data-seat-id="3" data-seat-action="leave" class="seat-leave hidden"><i class="fa fa-close"></i></button>
                <%--<div class="blue-team-color"></div>--%>
                <%--<div class="player-name_container" data-seat-name="blueTeamSeat1">${blueTeamSeat1}</div>--%>
            </div>
        </div>

        <%--gra--%>
        <div class="game-table_game">
            <div class="gamewords-row">
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
            </div>
            <div class="gamewords-row">
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
            </div>
            <div class="gamewords-row">
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
            </div>
            <div class="gamewords-row">
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
            </div>
            <div class="gamewords-row">
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
                <button class="gameword"><span></span></button>
            </div>

        </div>

        <%--gracze--%>
        <div class="game-table_players">
            <div class="player_container">
                <%--<span class="dot red-dot"></span>--%>
                <div id="redTeamSeat2Icon" class="icon-red"><img src="../../img/spy-icon.png" class="img-icon-red"></div>
                <button id="redTeamSeat2TakeASeat" data-seat-id="2" data-seat-action="take" class="seat-red">wolne miejsce</button>
                <div <%--id="playerSeat"--%> id="redTeamSeat2" class="player-name hidden">test</div>
                <button id="redTeamSeat2LeaveASeat" data-seat-id="2" data-seat-action="leave" class="seat-leave hidden"><i class="fa fa-close"></i></button>
                <%--<name>tymczasowy</name>--%>
                <%--<div class="red-team-color"></div>--%>
                <%--<div class="player-name_container" data-seat-name="redTeamSeat2">${redTeamSeat2}</div>--%>
            </div>
            <div class="gap">
                <form action="" method="post" id="clue-word-form" class="hidden">
                    <%--<label>Wskazówka:--%>
                        <input type="text" name="clueWord" placeholder="Wprowadź wskazówkę...">
                    <%--</label>--%>

                    <button type="submit">Wyślij</button>
                    <%--<input type="submit" value = "send">--%>
                </form>

                <button id="endTheTurn" class="hidden"><i class="fas fa-check"></i>&nbsp;&nbsp;&nbsp;&nbsp;Zakończ swoją rundę</button>

            </div>
            <div class="player_container">
                <%--<span class="dot blue-dot"></span>--%>
                <div id="blueTeamSeat2Icon" class="icon-blue"><img src="../../img/spy-icon.png" class="img-icon-blue"></div>
                <button id="blueTeamSeat2TakeASeat" data-seat-id="4" data-seat-action="take" class="seat-blue">wolne miejsce</button>
                <div <%--id="opponentSameRoleSeat"--%> id="blueTeamSeat2" class="player-name hidden">test</div>
                <button id="blueTeamSeat2LeaveASeat" data-seat-id="4" data-seat-action="leave" class="seat-leave hidden"><i class="fa fa-close"></i></button>
                <%--<div class="blue-team-color"></div>--%>
                <%--<div class="player-name_container" data-seat-name="blueTeamSeat2">${blueTeamSeat2}</div>--%>
            </div>
        </div>

    </div>
</div>


</body>
</html>
