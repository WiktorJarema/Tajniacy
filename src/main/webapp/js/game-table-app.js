$(document).ready(function () {

    // metody wykonywane na starcie
    getCurrentNickname();
    getGameWords();
    getGameAndUpdateClueAndWhoseTurn();
    getPlayersNames();

    // cykliczne wykonywanie zapytań
    var interval = setInterval(function () {
        getGameWords();
        getGameAndUpdateClueAndWhoseTurn();
        getPlayersNames();
    }, 5000);



    $("#endTheTurn").on("click", turnChange);

    $("#newGameWords").on("click", newGameWords);

    $("#redTeamSeat1TakeASeat").on("click", takeOrLeaveASeat);
    $("#redTeamSeat1LeaveASeat").on("click", takeOrLeaveASeat);
    $("#redTeamSeat2TakeASeat").on("click", takeOrLeaveASeat);
    $("#redTeamSeat2LeaveASeat").on("click", takeOrLeaveASeat);
    $("#blueTeamSeat1TakeASeat").on("click", takeOrLeaveASeat);
    $("#blueTeamSeat1LeaveASeat").on("click", takeOrLeaveASeat);
    $("#blueTeamSeat2TakeASeat").on("click", takeOrLeaveASeat);
    $("#blueTeamSeat2LeaveASeat").on("click", takeOrLeaveASeat);

    // wysyłanie wskazówki, kiedyś przerobić tak, aby funkcja była osobno
    $('#clue-word-form').on('submit', function(event) {
        event.preventDefault();

        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname,
            type: $(this).attr('method'),
            data: $(this).serialize(),
            dataType: "text",
            success: function() {
                // alert("Wysłałeś wskazówkę!");
                $("#clue-word").text($('input[name="clueWord"]').val());

                $("#clue-word-form").trigger("reset");

                changeWhoseTurn();
                // $('#clue-word-form').val('');
            }
        });
    });



    function checkGameWord() {
        console.log("kliknięcie");
        var gameWordId = $(this).attr("data-id");
        var currentButton = $(this);

        // if (jakiś warunek dodać) {

            $.ajax({
                url: "http://localhost:8080/tables" + window.location.pathname + "/checkgameword/" + gameWordId,
                // headers: {'Content-Type': 'application/json'},
                data: {},
                type: "GET",
                dataType: "json"
            })
                .done(function (result) {

                    // currentButton.attr("disabled",true);
                    currentButton.children(':first-child').addClass("hidden");
                    currentButton.on("mouseover", showGameWord).on("mouseleave", hideGameWord);
                    console.log(result.teamColour);
                    switch(result.teamColour) {
                        case "red":
                            currentButton.addClass("hit-red");
                            break;
                        case "blue":
                            currentButton.addClass("hit-blue");
                            break;
                        case "neutral":
                            currentButton.addClass("hit-neutral");
                            break;
                    }

                })
                .fail(function (xhr, status, err) {
                    console.log("Wystąpił błąd przy dodawaniu zajmowaniu miejsca");
                })

        // } else {
        //     alert("Coś poszło nie tak.");
        // }


    }



    function takeOrLeaveASeat() {
        var currentButton = $(this);
        var seatId = currentButton.attr("data-seat-id");
        var seatAction = currentButton.attr("data-seat-action");
        // var data = '{"seatId":"razz","action":"add"}';
        var data = "seatAction=" + seatAction;//"id=id&content=miejsce";

        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname + "/" + seatId,
            // headers: {'Content-Type': 'application/json'},
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: data,
            type: "PATCH",
            dataType: "text"
        })
            .done(function (result) {
                if (currentButton.attr("data-seat-action") == "take") {
                    $(".seat-red").attr("disabled",true);
                    $(".seat-blue").attr("disabled",true);
                    currentButton.addClass("hidden");
                    currentButton.next().removeClass("hidden").text(result).addClass("current-player");
                    currentButton.next().next().removeClass("hidden");

                    if (currentButton.attr("data-seat-id") == 1 || currentButton.attr("data-seat-id") == 3) {
                        $("#clue-word-form").removeClass("hidden");
                    } else {
                        $("#endTheTurn").removeClass("hidden");
                    }

                } else if (currentButton.attr("data-seat-action") == "leave") {
                    $(".seat-red").attr("disabled",false);
                    $(".seat-blue").attr("disabled",false);
                    currentButton.addClass("hidden");
                    currentButton.prev().addClass("hidden").removeClass("current-player");
                    currentButton.prev().prev().removeClass("hidden").text("wolne miejsce");

                    $("#clue-word-form").addClass("hidden");
                    $("#endTheTurn").addClass("hidden");
                }


            })
            .fail(function (xhr, status, err) {
                console.log("Wystąpił błąd przy dodawaniu zajmowaniu miejsca")
            })

    }



    function newGameWords() {

        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname + "/newgamewords",
            // headers: {'Content-Type': 'application/json'},
            data: {},
            type: "PATCH",
            dataType: "text"
        })
            .done(function (result) {

                getGameWords();
                // console.log(result);
                // var gameWordsButtons = $(".gameword");
                // for (var i = 0; i < 25; i++) {
                //     var currentButton = gameWordsButtons.eq(i);
                //     currentButton.attr("disabled", false);
                //     currentButton.removeClass("card-type-red");
                //     currentButton.removeClass("card-type-blue");
                //     currentButton.removeClass("card-type-neutral");
                //     currentButton.removeClass("hit-red");
                //     currentButton.removeClass("hit-blue");
                //     currentButton.removeClass("hit-neutral");
                // }

            })
            .fail(function (xhr, status, err) {
                console.log("Wystąpił błąd przy generowaniu nowych słów");
            })
    }



    function turnChange() {

        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname,
            // headers: {'Content-Type': 'application/json'},
            data: {},
            type: "PATCH",
            dataType: "text"
        })
            .done(function (result) {

                changeWhoseTurn();

            })
            .fail(function (xhr, status, err) {
                console.log("Wystąpił błąd przy zmienianiu tury");
            })
    }



    function changeWhoseTurn() {

        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname + "/checkwhoseturn",
            // headers: {'Content-Type': 'application/json'},
            data: {},
            type: "GET",
            dataType: "text"
        })
            .done(function (result) {

                console.log("wynik ze zmiany tury: " + result);

                if (result == "redTeamSeat1") {
                    $("#redTeamSeat2").removeClass("players-turn");
                    $("#blueTeamSeat1").removeClass("players-turn");
                    $("#blueTeamSeat2").removeClass("players-turn");
                    $("#redTeamSeat1").addClass("players-turn");
                } else if (result == "redTeamSeat2") {
                    $("#redTeamSeat1").removeClass("players-turn");
                    $("#blueTeamSeat1").removeClass("players-turn");
                    $("#blueTeamSeat2").removeClass("players-turn");
                    $("#redTeamSeat2").addClass("players-turn");
                } else if (result == "blueTeamSeat1") {
                    $("#redTeamSeat1").removeClass("players-turn");
                    $("#redTeamSeat2").removeClass("players-turn");
                    $("#blueTeamSeat2").removeClass("players-turn");
                    $("#blueTeamSeat1").addClass("players-turn");
                } else if (result == "blueTeamSeat2") {
                    $("#redTeamSeat1").removeClass("players-turn");
                    $("#redTeamSeat2").removeClass("players-turn");
                    $("#blueTeamSeat1").removeClass("players-turn");
                    $("#blueTeamSeat2").addClass("players-turn");
                } else {
                    console.log("nie za bardzo");
                }

            })
            .fail(function (xhr, status, err) {
                console.log("Wystąpił błąd sprawdzaniu czyja jest tura");
            })

    }



    function getPlayersNames() {

        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname,
            // headers: {'Content-Type': 'application/json'},
            data: {},
            type: "GET",
            dataType: "json"
        })
            .done(function (result) {

                var redTeamSeat1 = $("#redTeamSeat1");
                var redTeamSeat2 = $("#redTeamSeat2");
                var blueTeamSeat1 = $("#blueTeamSeat1");
                var blueTeamSeat2 = $("#blueTeamSeat2");

                // var redTeamSeat1PlayerName = "";
                // var redTeamSeat2PlayerName = "";
                // var blueTeamSeat1PlayerName = "";
                // var blueTeamSeat2PlayerName = "";

                var redTeamSeat1PlayerId = result.playerRedFirstId;
                var redTeamSeat2PlayerId = result.playerRedSecondId;
                var blueTeamSeat1PlayerId = result.playerBlueFirstId;
                var blueTeamSeat2PlayerId = result.playerBlueSecondId;

                if (redTeamSeat1PlayerId != 0) {
                    $.ajax({
                        url: "http://localhost:8080/nicknames/" + redTeamSeat1PlayerId,
                        data: {},
                        type: "GET",
                        dataType: "json",
                        success: function (result) {
                            playersName = result.name;
                            // console.log("players nickname wewnątrz: " + playersName);
                            redTeamSeat1.text(playersName);
                        }
                    })
                    redTeamSeat1.removeClass("hidden");
                    $("#redTeamSeat1TakeASeat").addClass("hidden");
                } else {
                    // redTeamSeat1.text("wolne");
                    redTeamSeat1.addClass("hidden");
                    $("#redTeamSeat1TakeASeat").removeClass("hidden");
                }

                if (redTeamSeat2PlayerId != 0) {
                    $.ajax({
                        url: "http://localhost:8080/nicknames/" + redTeamSeat2PlayerId,
                        data: {},
                        type: "GET",
                        dataType: "json",
                        success: function (result) {
                            playersName = result.name;
                            redTeamSeat2.text(playersName);
                        }
                    })
                    redTeamSeat2.removeClass("hidden");
                    $("#redTeamSeat2TakeASeat").addClass("hidden");
                } else {
                    // redTeamSeat2.text("wolne");
                    redTeamSeat2.addClass("hidden");
                    $("#redTeamSeat2TakeASeat").removeClass("hidden");
                }

                if (blueTeamSeat1PlayerId != 0) {
                    $.ajax({
                        url: "http://localhost:8080/nicknames/" + blueTeamSeat1PlayerId,
                        data: {},
                        type: "GET",
                        dataType: "json",
                        success: function (result) {
                            playersName = result.name;
                            blueTeamSeat1.text(playersName);
                        }
                    })
                    blueTeamSeat1.removeClass("hidden");
                    $("#blueTeamSeat1TakeASeat").addClass("hidden");
                } else {
                    // blueTeamSeat1.text("wolne");
                    blueTeamSeat1.addClass("hidden");
                    $("#blueTeamSeat1TakeASeat").removeClass("hidden");
                }

                if (blueTeamSeat2PlayerId != 0) {
                    $.ajax({
                        url: "http://localhost:8080/nicknames/" + blueTeamSeat2PlayerId,
                        data: {},
                        type: "GET",
                        dataType: "json",
                        success: function (result) {
                            playersName = result.name;
                            blueTeamSeat2.text(playersName);
                        }
                    })
                    blueTeamSeat2.removeClass("hidden");
                    $("#blueTeamSeat2TakeASeat").addClass("hidden");
                } else {
                    // blueTeamSeat2.text("wolne");
                    blueTeamSeat2.addClass("hidden");
                    $("#blueTeamSeat2TakeASeat").removeClass("hidden");
                }


                // sprawdzanie "mojego id" i na tej podstawie ustawianie przycisku wyłącz i bolda

                $.ajax({
                    url: "http://localhost:8080/currentnickname",
                    data: {},
                    type: "GET",
                    dataType: "json",
                    success: function (result) {
                        currentPlayerId = result.id;

                        if (currentPlayerId == redTeamSeat1PlayerId) {
                            $("#redTeamSeat1LeaveASeat").removeClass("hidden");
                            redTeamSeat1.addClass("current-player");
                            $("#clue-word-form").removeClass("hidden");
                        } else if (currentPlayerId == redTeamSeat2PlayerId) {
                            $("#redTeamSeat2LeaveASeat").removeClass("hidden");
                            redTeamSeat2.addClass("current-player");
                            $("#endTheTurn").removeClass("hidden");
                        } else if (currentPlayerId == blueTeamSeat1PlayerId) {
                            $("#blueTeamSeat1LeaveASeat").removeClass("hidden");
                            blueTeamSeat1.addClass("current-player");
                            $("#clue-word-form").removeClass("hidden");
                        } else if (currentPlayerId == blueTeamSeat2PlayerId) {
                            $("#blueTeamSeat2LeaveASeat").removeClass("hidden");
                            blueTeamSeat2.addClass("current-player");
                            $("#endTheTurn").removeClass("hidden");
                        }
                    }
                })

            })
            .fail(function (xhr, status, err) {
                console.log("Wystąpił błąd przy przypisywaniu nazw graczy do miejsc");
            })

    }



    function showGameWord() {
        $(this).children(':first-child').removeClass('hidden');
    }



    function hideGameWord() {
        $(this).children(':first-child').addClass('hidden');
    }



    // odbieranie słów
    function getGameWords() {
        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname + "/gamewords",
            // url: "http://localhost:8080/tables",
            //data: {},
            type: "GET",
            dataType: "json"
        })
            .done(function(result) {

                var gameWordsButtons = $(".gameword");

                // console.log(gameWordsButtons);

                for (var i = 0; i < result.length; i++) {

                    var currentButton = gameWordsButtons.eq(i);
                    // console.log(currentButton.children(':first-child'));
                    currentButton.children(':first-child').text(result[i].word);
                    currentButton.attr("data-id", result[i].id);
                    // currentButton.on("click", checkGameWord);

                    // if (gameWordsButtons.eq(5).hasClass('hit-blue')) {
                    // if (!gameWordsButtons.eq(5).is('.hit-blue, .hit-red, .hit-neutral')) {
                    //     console.log("słowo: " + gameWordsButtons.eq(5).children(":first-child").text());
                    //     console.log("ma klasę hit-blue");
                    //
                    // } else {
                    //     console.log("słowo: " + gameWordsButtons.eq(5).children(":first-child").text());
                    //     console.log("ma klasę hit-red");
                    // }

                    if (result[i].isHit == false) {
                        // console.log("cart type: " + result[i].teamColour);
                        // zmienić na cardType
                        currentButton.off();
                        currentButton.children(':first-child').removeClass('hidden');
                        currentButton.removeClass("hit-red");
                        currentButton.removeClass("hit-blue");
                        currentButton.removeClass("hit-neutral");
                        currentButton.removeClass("card-type-red");
                        currentButton.removeClass("card-type-blue");
                        currentButton.removeClass("card-type-neutral");
                        switch(result[i].teamColour) {
                            case "red":
                                currentButton.attr("disabled", true);
                                currentButton.addClass("card-type-red");
                                break;
                            case "blue":
                                currentButton.attr("disabled", true);
                                currentButton.addClass("card-type-blue");
                                break;
                            case "neutral":
                                currentButton.attr("disabled", true);
                                currentButton.addClass("card-type-neutral");
                                break;
                            case "unknown":
                                currentButton.on("click", checkGameWord);
                                break;
                        }
                    } else if (result[i].isHit == true) {
                        // w hasClass nie można stosowac or || dlatego używam is()
                        // if (currentButton.hasClass('hit-blue')) {
                        currentButton.attr("disabled", false);
                        if (!currentButton.is('.hit-blue, .hit-red, .hit-neutral')) {
                            // console.log(currentButton.children(":first-child").text() + " wszedł");
                            switch (result[i].teamColour) {
                                case "red":
                                    // currentButton.attr("disabled",true);
                                    currentButton.removeClass("card-type-red");
                                    currentButton.addClass('hit-red');
                                    currentButton.children(':first-child').addClass("hidden");
                                    currentButton.on("mouseover", showGameWord).on("mouseleave", hideGameWord);
                                    break;
                                case "blue":
                                    // currentButton.prop("disabled",true);
                                    currentButton.removeClass("card-type-blue");
                                    currentButton.addClass('hit-blue');
                                    currentButton.children(':first-child').addClass("hidden");
                                    currentButton.on("mouseover", showGameWord).on("mouseleave", hideGameWord);
                                    break;
                                case "neutral":
                                    // currentButton.prop("disabled",true);
                                    currentButton.removeClass("card-type-neutral");
                                    currentButton.addClass('hit-neutral');
                                    currentButton.children(':first-child').addClass("hidden");
                                    currentButton.on("mouseover", showGameWord).on("mouseleave", hideGameWord);
                                    break;
                            }
                        }
                    }
                }

            })
            .fail(function(xhr,status,err) {
                console.log("błąd z odbiorem slów")
            })
    }



    // odbieranie gry, wykorzystanie informacji o wskazówce i czyja jest kolej
    function getGameAndUpdateClueAndWhoseTurn() {
        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname + "/game",
            //data: {},
            type: "GET",
            dataType: "json"
        })
            .done(function(result) {

                var clueWord = $("#clue-word");
                clueWord.text(result.clueWord);

                var whoseTurn = result.playerTurnName;
                console.log(whoseTurn);

                if (whoseTurn == "redTeamSeat1") {
                    $("#blueTeamSeat2").removeClass("players-turn");
                    $("#redTeamSeat1").addClass("players-turn");
                } else if (whoseTurn == "redTeamSeat2") {
                    $("#redTeamSeat1").removeClass("players-turn");
                    $("#redTeamSeat2").addClass("players-turn");
                } else if (whoseTurn == "blueTeamSeat1") {
                    $("#redTeamSeat2").removeClass("players-turn");
                    $("#blueTeamSeat1").addClass("players-turn");
                } else if (whoseTurn == "blueTeamSeat2") {
                    $("#blueTeamSeat1").removeClass("players-turn");
                    $("#blueTeamSeat2").addClass("players-turn");
                }

            })
            .fail(function(xhr,status,err) {
                console.log("błąd z odbiorem slów")
            })
    }



    // pytanie o mój nickname
    function getCurrentNickname() {
        $.ajax({
            url: "http://localhost:8080/currentnickname",
            //data: {},
            type: "GET",
            dataType: "json"
        })
            .done(function(result) {

                var nicknameName = result.name;
                $("#nickname").text("Twój nick: " + nicknameName);
                console.log("chyba działa");

                console.log(nicknameName);

            })
            .fail(function(xhr,status,err) {
                console.log("Wystąpił błąd!")
            })
    }








});