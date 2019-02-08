$(document).ready(function () {


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

                    currentButton.attr("disabled",true);
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










    // // przykład do kopiowania
    // $.ajax({
    //     url: "http://localhost:8080/tables",
    //     //data: {},
    //     type: "GET",
    //     dataType: "json"
    // })
    //     .done(function(result) {
    //
    //         for (var i = 0; i < result.length; i++) {
    //
    //             // var bookTitle = $("<h1>").text(result[i].title).addClass("book-title");
    //             var gameTableName = $("<a>").attr("href", result[i].name.toLowerCase()).text(result[i].name).addClass("game-table-name").addClass("link");
    //             gameTableName.attr("data-id", result[i].id);
    //             gameTableName.attr("data-name", result[i].name);
    //
    //             // var deleteBookButton = $("<a>").attr("href", "#").text("Usuń książkę").addClass("delete-button").addClass("link");
    //
    //             var gameTableDiv = $("<div>").addClass("game-table-div");
    //             // var bookDetailsRowDiv = $("<div>").addClass("book-details-row-div").css("display", "none");
    //
    //             var gameTableNameDiv = $("<div>").addClass("game-table-name-div");
    //             var playersDiv = $("<div>").addClass("players-div");
    //
    //             gameTableNameDiv.append(gameTableName);
    //             // playersDiv.append(deleteBookButton);
    //             gameTableDiv.append(gameTableNameDiv).append(playersDiv);
    //
    //             $("#list_container").append(gameTableDiv);
    //             // $("#list_container").append(gameTableDiv).append(bookDetailsRowDiv);
    //
    //             // bookTitle.on("click", getBookDetails);
    //             // deleteBookButton.on("click", deleteBook);
    //
    //         }
    //     })
    //     .fail(function(xhr,status,err) {
    //         console.log("Wystąpił błąd!")
    //     })


    // odebranie słów
    var interval = setInterval(function () {

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
                    console.log(currentButton.children(':first-child'));
                    currentButton.children(':first-child').text(result[i].word);
                    currentButton.attr("data-id", result[i].id);
                    currentButton.on("click", checkGameWord);

                    if (result[i].isHit == false) {
                        // console.log("cart type: " + result[i].teamColour);
                        // zmienić na cardType
                        switch(result[i].teamColour) {
                            case "red":
                                currentButton.attr("disabled",true);
                                currentButton.addClass("card-type-red");
                                break;
                            case "blue":
                                currentButton.prop("disabled",true);
                                currentButton.addClass("card-type-blue");
                                break;
                            case "neutral":
                                currentButton.prop("disabled",true);
                                currentButton.addClass("card-type-neutral");
                                break;
                        }
                    } else if (result[i].isHit == true) {
                        switch(result[i].teamColour) {
                            case "red":
                                // currentButton.attr("disabled",true);
                                currentButton.removeClass("card-type-red");
                                currentButton.addClass("hit-red");
                                currentButton.children(':first-child').addClass("hidden");
                                break;
                            case "blue":
                                // currentButton.prop("disabled",true);
                                currentButton.removeClass("card-type-blue");
                                currentButton.addClass("hit-blue");
                                currentButton.children(':first-child').addClass("hidden");
                                break;
                            case "neutral":
                                // currentButton.prop("disabled",true);
                                currentButton.removeClass("card-type-neutral");
                                currentButton.addClass("hit-neutral");
                                currentButton.children(':first-child').addClass("hidden");
                                break;
                        }
                    }
                }

            })
            .fail(function(xhr,status,err) {
                console.log("błąd z odbiorem slów")
            })

    // odbieranie wskazówki, przez odbiór gry
    // var interval = setInterval(function () {
        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname + "/game",
            //data: {},
            type: "GET",
            dataType: "json"
        })
            .done(function(result) {

                var clueWord = $("#clue-word");
                // console.log(result.clueWord);
                clueWord.text(result.clueWord);

            })
            .fail(function(xhr,status,err) {
                console.log("błąd z odbiorem slów")
            })

    }, 5000);

    // wysyłanie wskazówki
    $('#clue-word-form').on('submit', function(e) {
        e.preventDefault();

        $.ajax({
            url: "http://localhost:8080/tables" + window.location.pathname,
            type: $(this).attr('method'),
            data: $(this).serialize(),
            dataType: "text",
            success: function() {
                // alert("Wysłałeś wskazówkę!");
                $("#clue-word").text($('input[name="clueWord"]').val());

                $("#clue-word-form").trigger("reset");

                // $('#clue-word-form').val('');
            }
        });

    });


    // wysyłanie wskazówki, inna metoda, nie dokończona
    // $.ajax({
    //     url: "http://localhost:8080/tables" + window.location.pathname,
    //     //data: {},
    //     type: "POST",
    //     dataType: "json"
    // })
    //     .done(function(result) {
    //
    //         var clueWord = $("#clue-word");
    //         console.log(result.clueWord);
    //         clueWord.text(result.clueWord);
    //
    //     })
    //     .fail(function(xhr,status,err) {
    //         console.log("błąd z odbiorem slów")
    //     })



    // pytanie o mój nickname
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







});