$(document).ready(function () {

    // var host = "tajniacy.eu-central-1.elasticbeanstalk.com";
    var host = "www.tajniacy.org";
    // var host = "localhost:8080";

    // metody wykonywane na starcie
    getTables();
    getNickname();
    getNumberOfUsersOnline();

    // cykliczne wykonywanie zapytań
    var interval = setInterval(function () {
        resetSessionTimeout();
        getNumberOfUsersOnline();
    }, 5000);


    
    // utworzenie stołów w home
    function getTables() {
        $.ajax({
            url: "http://" + host + "/tables",
            //data: {},
            type: "GET",
            dataType: "json"
        })
            .done(function(result) {

                for (var i = 0; i < result.length; i++) {

                    // var bookTitle = $("<h1>").text(result[i].title).addClass("book-title");
                    var gameTableName = $("<a>").attr("href", result[i].name.toLowerCase()).text(result[i].name).addClass("game-table-name").addClass("link");
                    gameTableName.attr("data-id", result[i].id);
                    gameTableName.attr("data-name", result[i].name);

                    // var deleteBookButton = $("<a>").attr("href", "#").text("Usuń książkę").addClass("delete-button").addClass("link");

                    var gameTableDiv = $("<div>").addClass("game-table-div");
                    // var bookDetailsRowDiv = $("<div>").addClass("book-details-row-div").css("display", "none");

                    var gameTableNameDiv = $("<div>").addClass("game-table-name-div");
                    var playersDiv = $("<div>").addClass("players-div");

                    gameTableNameDiv.append(gameTableName);
                    // playersDiv.append(deleteBookButton);
                    gameTableDiv.append(gameTableNameDiv).append(playersDiv);

                    $("#list_container").append(gameTableDiv);
                    // $("#list_container").append(gameTableDiv).append(bookDetailsRowDiv);

                    // bookTitle.on("click", getBookDetails);
                    // deleteBookButton.on("click", deleteBook);

                }
            })
            .fail(function(xhr,status,err) {
                console.log("Wystąpił błąd!")
            })
    }


    // pytanie o mój nickname
    function getNickname() {
        $.ajax({
            url: "http://" + host + "/currentnickname",
            //data: {},
            type: "GET",
            dataType: "json"
        })
            .done(function(result) {



                var nicknameName = result.name;
                $("#nickname").text("Twój nick: " + nicknameName);
                // console.log(nicknameName);

            })
            .fail(function(xhr,status,err) {
                console.log("Wystąpił błąd!")
            })

    }


    // reset MaxInactiveInterval sesji
    function resetSessionTimeout() {
        $.ajax({
            url: "http://" + host + "/resetsessiontimeout",
            data: {},
            type: "PATCH",
            dataType: "text"
        })
            .done(function(result) {

                // console.log("Ok reset MaxInactiveInterval");

            })
            .fail(function(xhr,status,err) {
                console.log("Błąd resetu MaxInactiveInterval");
            })
    }


    // pytanie o liczbę użytkowników online
    function getNumberOfUsersOnline() {
        $.ajax({
            url: "http://" + host + "/numberofusersonline",
            //data: {},
            type: "GET",
            dataType: "text"
        })
            .done(function (result) {

                $("#number-online-users").text(result);
                // console.log(result);

            })
            .fail(function (xhr, status, err) {
                console.log("Wystąpił błąd przy pobieraniu informacji o liczbie użytkowników online")
            })
    }


});