package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.tajniacy.model.*;
import org.tajniacy.service.GameService;
import org.tajniacy.service.GameTableService;
import org.tajniacy.service.NicknameService;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.List;

@RestController
public class GameTableRestController {

    @Autowired
    GameTableService gameTableService;

    @Autowired
    NicknameService nicknameService;

    @Autowired
    GameService gameService;


    @GetMapping(path = "/tables")
    public List<GameTable> getAllGameTables() {
        return gameTableService.findAllGameTables();
    }

    @GetMapping(path = "/tables/{gameTableName}")
    public GameTable getAllGameTables(@PathVariable(name = "gameTableName") String gameTableName,
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
    public Game newGame(@PathVariable(name = "gameTableName") String gameTableName,
                               HttpSession session) {

        GameTable gameTable = gameTableService.findGameTableByName(gameTableName);
        Game game = gameTableService.createNewGame(gameTable);
//        return game.getAllWord();
        return game;

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

    // działa, to na później, na razie robię przydzielenie gracza od razu po wejściu na stół w homeController
    @PostMapping(path = "/tables/{gameTableName}/seats")
    public String addPlayerToGameTable(@PathVariable(name = "gameTableName") String gameTableName,
                                       @RequestBody MultiValueMap<String, String> info,
                                       HttpSession session) {
        System.out.println(info);
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
        return "ok";

    }

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
