package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.tajniacy.model.*;
import org.tajniacy.service.GameService;
import org.tajniacy.service.GameTableService;
import org.tajniacy.service.GameWordService;
import org.tajniacy.service.NicknameService;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@Transactional
public class GameTableRestController {

    @Autowired
    GameTableService gameTableService;

    @Autowired
    NicknameService nicknameService;

    @Autowired
    GameService gameService;

    @Autowired
    GameWordService gameWordService;


    @GetMapping(path = "/tables")
    public List<GameTable> getAllGameTables() {
        return gameTableService.findAllGameTables();
    }

    @GetMapping(path = "/tables/{gameTableName}")
    public GameTable getGameTable(@PathVariable(name = "gameTableName") String gameTableName,
                                      HttpSession session) {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject == null) {
            return null;
        } else {
            return gameTableService.findGameTableByName(gameTableName);
        }

    }

    // na razie dla testów
    @GetMapping(path = "/tables/{gameTableName}/newgame")
    public List<GameWord> newGame(@PathVariable(name = "gameTableName") String gameTableName,
                               HttpSession session) {

        GameTable gameTable = gameTableService.findGameTableByName(gameTableName);
        Game game = gameTableService.createNewGame(gameTable);

        return game.getGameWords();

    }

    // na razie dla testów
    @PatchMapping(path = "/tables/{gameTableName}/newgamewords")
    public String newGameWords(@PathVariable(name = "gameTableName") String gameTableName,
                                  HttpSession session) {

        GameTable gameTable = gameTableService.findGameTableByName(gameTableName);
        gameService.createNewGameWords(gameTable);

        return "wygenerowane nowe słowa";

    }

    @GetMapping(path = "/tables/{gameTableName}/game")
    public Game getGame(@PathVariable(name = "gameTableName") String gameTableName,
                        HttpSession session) {

        Game game = gameService.findGameByGameTableName(gameTableName);
        return game;

    }

    // to myślę, że nie potrzebne, całą grę będę zwracał
//    @GetMapping(path = "/tables/{gameTableName}/getwhoseturn")
//    public String getWhoseTurn(@PathVariable(name = "gameTableName") String gameTableName,
//                               HttpSession session) {
//
//        return gameTableService.getWhoseTurnSeatName(gameTableName);
//
//    }

    @PostMapping(path = "tables/{gameTableName}", produces = "text/html; charset=UTF-8")
    public String passClue(@PathVariable(name = "gameTableName") String gameTableName,
                           @RequestParam(name = "clueWord") String clueWord,
                           HttpSession session) {
//        System.out.println(clueWord);
        Game game = gameService.findGameByGameTableName(gameTableName);
        game.setClueWord(clueWord);

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject != null) {
            Nickname usedNickname = (Nickname) nicknameObject;

            // ture może zmienić tylko gracz, którego jest obecnie tura
            if (gameService.checkIfItIsHisTurn(gameTableName, usedNickname)) {
                System.out.println("wewnątrz ifa od zmiany tury");
                gameService.turnChange(gameTableName);
                return "Wskazówka przyjęta, tura zmieniona";
            } else {
                return "Nie możesz zmienic tury, bo to nie twoja kolej";
            }


        }
        return "nie ma cię na stole";
        // to były jak nie było sprawdzania czy jest to tura gracza, który chce zmienić turę
//        gameService.turnChange(gameTableName);
//        return "Wskazówka przyjęta";
    }

    // kopia z zadań z modułu 5
//    @ResponseBody
//    @PostMapping(path = "/form", produces = "text/html; charset=UTF-8")
//    public String showParam(@RequestParam(name = "paramName") String param) {
//        return "Wartość parametru: " + param;
//    }

    @GetMapping(path = "/tables/{gameTableName}/gamewords")
    public List<GameWord> getGameWords(@PathVariable(name = "gameTableName") String gameTableName,
                                       HttpSession session) {


        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject == null) {
            return null;
        } else {
            Nickname usedNickname = (Nickname) nicknameObject;
            GameTable gameTable = gameTableService.findGameTableByName(gameTableName);
            Game game = gameService.findGameByGameTableName(gameTableName);
//            System.out.println("Zapytanie od: " + usedNickname.getName());
//            System.out.println("Id gracza red team seat 1: " + gameTable.getPlayerRedFirstId());
//            System.out.println("Id gracza red team seat 2: " + gameTable.getPlayerRedSecondId());
//            System.out.println("Id gracza blue team seat 1: " + gameTable.getPlayerBlueFirstId());
//            System.out.println("Id gracza blue team seat 2: " + gameTable.getPlayerBlueSecondId());
            List<GameWord> originalGameWords = game.getGameWords();

            if (usedNickname.getId() == gameTable.getPlayerRedFirstId() || usedNickname.getId() == gameTable.getPlayerBlueFirstId()) {
//                System.out.println("słowa oryginalne");
                return originalGameWords;
            } else {
//                System.out.println("słowa zmienione");
                List <GameWord> gameWordsWithCardTypeUnknown = new ArrayList<>();
                for(GameWord element : originalGameWords) {
                    GameWord newElement = element.clone();
                    if (!newElement.getIsHit()) {
                        newElement.setTeamColour("unknown");
                    }
                    gameWordsWithCardTypeUnknown.add(newElement);
                }

                return gameWordsWithCardTypeUnknown;
            }

        }

    }


    @GetMapping(path = "/tables/{gameTableName}/checkgameword/{gameWordId}")
    public GameWord checkGameWord(@PathVariable(name = "gameTableName") String gameTableName,
                                       @PathVariable(name = "gameWordId") Long gameWordId,
                                       HttpSession session) {

        System.out.println("początek metody sprawdzania słowa");

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject == null) {
            return null;
        } else {
            Nickname usedNickname = (Nickname) nicknameObject;
            GameTable gameTable = gameTableService.findGameTableByName(gameTableName);
            Game game = gameService.findGameByGameTableName(gameTableName);
            String whoseTurn = game.getPlayerTurnName();



            if (gameService.checkIfItIsHisTurn(gameTableName, usedNickname)) {
                GameWord gameWord = gameWordService.findGameWordById(gameWordId);
                String playersSeatName = gameService.getPlayersCurrentSeatName(gameTableName, usedNickname);
                if (playersSeatName.equals("redTeamSeat1") || playersSeatName.equals("redTeamSeat2")) {
                    if (!gameWord.getTeamColour().equals("red")) {
                        gameService.turnChange(gameTableName);
                    }
                } else if (playersSeatName.equals("blueTeamSeat1") || playersSeatName.equals("blueTeamSeat2")) {
                    if (!gameWord.getTeamColour().equals("blue")) {
                        gameService.turnChange(gameTableName);
                    }
                }
                gameWord.setIsHit(true);
                return gameWord;
            } else {
                return null;
            }
        }

    }

    // to jest tylko dla potrzeb testowania, normlanie dla potrzeb REST powirnien tu być DeleteMapping
    @GetMapping(path = "/tables/{gameTableName}/deletegame", produces = "text/html; charset=UTF-8")
    public String deleteGame(@PathVariable(name = "gameTableName") String gameTableName,
                     HttpSession session) {

//        Game game = gameService.findGameByGameTableName(gameTableName);
//        System.out.println(game);
//        gameService.deleteGame(game);
        gameTableService.deleteGame(gameTableName);
//        gameService.deleteGameById(game.getId());
//        gameService.deleteGameByGameTableName(gameTableName);
        return "gra usunięta";

    }

    @GetMapping(path = "/games")
    public List<Game> getAllGames() {
        return gameService.findAllGames();
    }

//    @GetMapping(path = "/tables/{gameTableName}/game/words")
//    public List<GameWord> getGameWords(@PathVariable(name = "gameTableName") String gameTableName,
//                                   HttpSession session) {
//
//        GameTable gameTable = gameTableService.findGameTableByName(gameTableName);
//        Game game = gameTable.getGame();
//
//        return game.getGameWords();
//
//    }

//    @PutMapping(path = "/tables/{gameTableName}/addplayer/{seatNumber}")
//    public String addPlayerToGameTable(@PathVariable(name = "gameTableName") String gameTableName,
//                                      @PathVariable(name = "seatNumber") Long seatNumber,
//                                      HttpSession session) {
//
//        Object nicknameObject = session.getAttribute("nickname");
//        if (nicknameObject != null) {
//            Nickname usedNickname = (Nickname) nicknameObject;
//            Long playerId = usedNickname.getId();
//            gameTableService.addPlayerToGameTable(playerId, gameTableName, seatNumber);
//
//
//            return usedNickname.getName();
//        } else {
//            return null;
//        }
//
//    }

    @PatchMapping(path = "/tables/{gameTableName}/{seatId:\\d{1}}")
    public String addPlayerToGameTable(@PathVariable(name = "gameTableName") String gameTableName,
                                       @PathVariable (name = "seatId") Long seatId,
                                       @RequestBody MultiValueMap<String, String> seat,
                                       HttpSession session) {

        String seatAction = seat.getFirst("seatAction");
        System.out.println("miejsce: " + seatId + "; akcja: " + seatAction);

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject != null) {
            Nickname usedNickname = (Nickname) nicknameObject;
            Long playerId = usedNickname.getId();

            if (seatAction.equals("take")) {
                gameTableService.addPlayerToGameTable(gameTableName, seatId, playerId);
            } else if (seatAction.equals("leave")) {
                gameTableService.deletePlayerFromGameTable(gameTableName, seatId, playerId);
            } else {
                return null;
            }

            return usedNickname.getName();
        } else {
            return null;
        }

    }

    @PatchMapping(path = "/tables/{gameTableName}")
    public String turnChange(@PathVariable(name = "gameTableName") String gameTableName,
                                       HttpSession session) {

        System.out.println("początek metody od zmiany tury");

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject != null) {
            System.out.println("zmiana tury, nickname jest w sesji");
            Nickname usedNickname = (Nickname) nicknameObject;

            // ture może zmienić tylko gracz, którego jest obecnie tura
            if (gameService.checkIfItIsHisTurn(gameTableName, usedNickname)) {
                System.out.println("wewnątrz ifa od zmiany tury");
                gameService.turnChange(gameTableName);
                return "Tura zmieniona";
            } else {

                System.out.println();

                return "coś nie halo";
            }


        }
        return "Tura bez zmian";
    }

    @GetMapping(path = "/tables/{gameTableName}/checkwhoseturn")
    public String checkWhoseTurn(@PathVariable(name = "gameTableName") String gameTableName,
                             HttpSession session) {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject != null) {

            Game game = gameService.findGameByGameTableName(gameTableName);
            return game.getPlayerTurnName();

        }
        return null;
    }


    // działa, to na później, na razie robię przydzielenie gracza od razu po wejściu na stół w homeController,
    // to zrobiłem na zajęciach i działało, ale teraz inacej robie, metowa wyżej
//    @PostMapping(path = "/tables/{gameTableName}/seats")
//    public String addPlayerToGameTable(@PathVariable(name = "gameTableName") String gameTableName,
//                                       @RequestBody MultiValueMap<String, String> info,
//                                       HttpSession session) {
//        System.out.println(info);
////        Object nicknameObject = session.getAttribute("nickname");
////        if (nicknameObject != null) {
////            Nickname usedNickname = (Nickname) nicknameObject;
////            Long playerId = usedNickname.getId();
////            gameTableService.addPlayerToGameTable(playerId, gameTableName, seatNumber);
////
////
////            return usedNickname.getName();
////        } else {
////            return null;
////        }
//        return "ok";
//
//    }

//    @PUT
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateBook(@Valid Book book, @PathParam("id") long id) {
//        int matchIdx = 0;
//        if (book.getId() != id) {
//            throw new NotFoundException(new JsonError("Error", "Request id is not equal book.id"));
//        }
//        Optional<Book> match = cList.stream().filter(c -> c.getId() == id).findFirst();
//        if (match.isPresent()) {
//            matchIdx = cList.indexOf(match.get());
//            cList.set(matchIdx, book);
//            return Response.status(204).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }

    // trzeba zrobić!
//    @PostMapping(path = "/{gameTableName}")
//    public GameTable assignPlayerToTable(@PathVariable(name = "gameTableName") String gameTableName) {

    // dopisz gracza


//        return gameTableService.findGameTableByName(gameTableName);
//    }

}
