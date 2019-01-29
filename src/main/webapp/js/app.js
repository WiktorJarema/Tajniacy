$(document).ready(function () {


    // var seatDownButton = $("#seat-down");
    // seatDownButton.on("click", seatDown);
    //
    //
    // function seatDown() {
    //     console.log("kliknięcie");
    //     console.log(window.location.pathname);
    //     var seatId = $(this).attr("data-seat-id");
    //     console.log(seatId);
    //
    //     // if (jakiś warunek dodać) {
    //
    // // @PutMapping(path = "/tables/{gameTableName}/addplayer/{seatNumber}")
    //         $.ajax({
    //             url: "http://localhost:8080/tables" + window.location.pathname + "/addplayer/" + seatId,
    //             // headers: {'Content-Type': 'application/json'},
    //             data: {},
    //             type: "PUT",
    //             dataType: "json"
    //         })
    //             .done(function (result) {
    //                 console.log("done ok");
    //                 alert("miejsce zajęte");
    //             })
    //             .fail(function (xhr, status, err) {
    //                 console.log("Wystąpił błąd przy dodawaniu zajmowaniu miejsca")
    //             })
    //
    //     // } else {
    //     //     alert("Coś poszło nie tak.");
    //     // }
    //
    //
    //     // seatDownButton.css("display", "none");
    //     seatDownButton.addClass("hide");
    //     seatDownButton.next().removeClass("hide");
    //     seatDownButton.next().next().removeClass("hide");
    //
    // }










    // utworzenie stołów w home
    $.ajax({
        url: "http://localhost:8080/tables",
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



    function goToTable() {

        var tableName = $(this).attr("data-name");
        // var detailsRow = $(this).parent().parent().next();
        // var bookTitle = $(this);
        console.log(tableName);


        if (detailsRow.children().length == 0) {

            $.ajax({
                url: "http://localhost:8282/" + tableName,
                type: "GET",
                dataType: "json"
            })
                .done(function (result) {

                    var bookAuthor = $("<p>").text("Autor: " + result.author);
                    var bookPublisher = $("<p>").text("Wydawca: " + result.publisher);
                    var bookType = $("<p>").text("Tematyka: " + result.type);
                    var bookIsbn = $("<p>").text("ISBN: " + result.isbn);

                    var bookDetailsDiv = $("<div>").addClass("book-details-div");
                    var buttonEditDiv = $("<div>").addClass("button-div");

                    bookDetailsDiv.append(bookAuthor).append(bookPublisher).append(bookType).append(bookIsbn);
                    buttonEditDiv.text("test");
                    detailsRow.append(bookDetailsDiv).append(buttonEditDiv);

                    // toggleBookDetails();
                    // $("#list_container").find("h1").on("click", toggleBookDetails);

                    // detailsRow.slideToggle();
                    // $(this).on("click", function () {
                    // $("#list_container").find("h1").on("click", function () {

                    detailsRow.slideToggle();
                    bookTitle.on("click", function () {
                        detailsRow.slideToggle();
                    })


                })
                .fail(function (xhr, status, err) {
                    console.log("Wystąpił błąd przy pobieraniu szczegółów książki!")
                })

        }
        return false;
    }







    // function getBookDetails() {
    //
    //     var id = $(this).attr("data-id");
    //     var detailsRow = $(this).parent().parent().next();
    //     var bookTitle = $(this);
    //
    //
    //     if (detailsRow.children().length == 0) {
    //
    //         $.ajax({
    //             url: "http://localhost:8080/books/" + id,
    //             type: "GET",
    //             dataType: "json"
    //         })
    //             .done(function (result) {
    //
    //                 var bookAuthor = $("<p>").text("Autor: " + result.author);
    //                 var bookPublisher = $("<p>").text("Wydawca: " + result.publisher);
    //                 var bookType = $("<p>").text("Tematyka: " + result.type);
    //                 var bookIsbn = $("<p>").text("ISBN: " + result.isbn);
    //
    //                 var bookDetailsDiv = $("<div>").addClass("book-details-div");
    //                 var buttonEditDiv = $("<div>").addClass("button-div");
    //
    //                 bookDetailsDiv.append(bookAuthor).append(bookPublisher).append(bookType).append(bookIsbn);
    //                 buttonEditDiv.text("test");
    //                 detailsRow.append(bookDetailsDiv).append(buttonEditDiv);
    //
    //                 // toggleBookDetails();
    //                 // $("#list_container").find("h1").on("click", toggleBookDetails);
    //
    //                 // detailsRow.slideToggle();
    //                 // $(this).on("click", function () {
    //                 // $("#list_container").find("h1").on("click", function () {
    //
    //                 detailsRow.slideToggle();
    //                 bookTitle.on("click", function () {
    //                     detailsRow.slideToggle();
    //                 })
    //
    //
    //             })
    //             .fail(function (xhr, status, err) {
    //                 console.log("Wystąpił błąd przy pobieraniu szczegółów książki!")
    //             })
    //
    //     }
    //     return false;
    // }



    // function toggleBookDetails() {
    //
    //     // var detailsRow = $("#list_container").children().eq(1);
    //     var detailsRow = $(this).parent().parent().next();
    //     detailsRow.slideToggle();
    //
    // }




    // function deleteBook() {
    //
    //     var bookId = $(this).parent().parent().find(".book-title").attr("data-id");
    //     // var detailsRow = $(this).parent().parent().next();
    //     // var bookTitle = $(this);
    //     console.log(bookId);
    //
    //
    //     // if (detailsRow.children().length == 0) {
    //     //
    //     //     $.ajax({
    //     //         url: "http://localhost:8282/books/" + id,
    //     //         type: "GET",
    //     //         dataType: "json"
    //     //     })
    //     //         .done(function (result) {
    //     //
    //     //             var bookAuthor = $("<p>").text("Autor: " + result.author);
    //     //             var bookPublisher = $("<p>").text("Wydawca: " + result.publisher);
    //     //             var bookType = $("<p>").text("Tematyka: " + result.type);
    //     //             var bookIsbn = $("<p>").text("ISBN: " + result.isbn);
    //     //
    //     //             var bookDetailsDiv = $("<div>").addClass("book-details-div");
    //     //             var buttonEditDiv = $("<div>").addClass("button-div");
    //     //
    //     //             bookDetailsDiv.append(bookAuthor).append(bookPublisher).append(bookType).append(bookIsbn);
    //     //             buttonEditDiv.text("test");
    //     //             detailsRow.append(bookDetailsDiv).append(buttonEditDiv);
    //     //
    //     //             // toggleBookDetails();
    //     //             // $("#list_container").find("h1").on("click", toggleBookDetails);
    //     //
    //     //             // detailsRow.slideToggle();
    //     //             // $(this).on("click", function () {
    //     //             // $("#list_container").find("h1").on("click", function () {
    //     //
    //     //             detailsRow.slideToggle();
    //     //             bookTitle.on("click", function () {
    //     //                 detailsRow.slideToggle();
    //     //             })
    //     //
    //     //
    //     //         })
    //     //         .fail(function (xhr, status, err) {
    //     //             console.log("Wystąpił błąd przy pobieraniu szczegółów książki!")
    //     //         })
    //     //
    //     // }
    //     return false;
    // }













});