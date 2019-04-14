package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.listener.NicknameSessionListener;
import org.tajniacy.model.Game;
import org.tajniacy.model.GameTable;
import org.tajniacy.model.GameWord;
import org.tajniacy.model.Nickname;
import org.tajniacy.repository.GameJpaRepository;
import org.tajniacy.repository.GameTableJpaRepository;
import org.tajniacy.repository.GameWordJpaRepository;
import org.tajniacy.service.GameService;
import org.tajniacy.service.GameTableService;
import org.tajniacy.service.NicknameService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class HomeController {

    @Autowired
    NicknameService nicknameService;

    @Autowired
    GameTableService gameTableService;

    @Autowired
    GameService gameService;


    @ResponseBody
    @GetMapping(path = "/test", produces = "text/html; charset=UTF-8")
    public String tests() {

        List<Nickname> list = nicknameService.getAllNicknames();


        for (Nickname element : list) {
            System.out.println(element.getName());
        }

        System.out.println();
        System.out.println();

        System.out.println(list.get(10).getName());

        return "Koniec metody tests";
    }


    @GetMapping(path = "/start", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String start() {

        gameTableService.createNewTable("Copacabana");
        gameTableService.createNewTable("Saragossa");
        gameTableService.createNewTable("Sandomierz");
        gameTableService.createNewTable("Makau");
        gameTableService.createNewTable("Casablanca");
        gameTableService.createNewTable("Jurata");
        gameTableService.createNewTable("Sicilia");

//        GameTable gameTable = gameTableService.findGameTableByName("copacabana");
//        gameTableService.createNewGame(gameTable);

        return "jest ok";
    }


    @GetMapping(path = "/home", produces = "text/html; charset=UTF-8")
    public String homePage(HttpSession session, Model model) throws NicknameNotFoundException {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject == null) {
            Nickname newNickname = nicknameService.getNextFreeNickname();
            session.setAttribute("nickname", newNickname);
            session.setMaxInactiveInterval(10);

            model.addAttribute("welcomeMessage", "Cześć, to będzie Twój nick: " + newNickname.getName());
            return "index";
        } else {
            Nickname usedNickname = (Nickname) nicknameObject;
            model.addAttribute("welcomeMessage", "A my się już znamy, cześć " + usedNickname.getName());
            return "index";
        }

    }


    @GetMapping(path = "/{gameTableName}")
    public String getGameTable(@PathVariable(name = "gameTableName") String gameTableName,
                                   HttpSession session,
                                   Model model) throws NicknameNotFoundException {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject == null) {
            return "redirect:http://google.pl";
        } else {

            model.addAttribute("gameTable", gameTableService.findGameTableByName(gameTableName).getName());
// wyłączam, bo realizuję dodawanie do stołu "normalnie"
//            Nickname usedNickname = (Nickname) nicknameObject;
//            Long playerId = usedNickname.getId();
//            gameTableService.addPlayerToGameTable(gameTableName, playerId);
//
//            model.addAttribute("redTeamSeat1", gameTableService.getPlayerNameFromRedTeamSeat1(gameTableName));
//            model.addAttribute("redTeamSeat2", gameTableService.getPlayerNameFromRedTeamSeat2(gameTableName));
//            model.addAttribute("blueTeamSeat1", gameTableService.getPlayerNameFromBlueTeamSeat1(gameTableName));
//            model.addAttribute("blueTeamSeat2", gameTableService.getPlayerNameFromBlueTeamSeat2(gameTableName));

            return "game-table";
        }

    }

//    @GetMapping(path = "/", produces = "text/html; charset=UTF-8")
//    public String test() {
//
//        return "index";
//    }

    @GetMapping(path = "/zero", produces = "text/html; charset=UTF-8")
    public void setPlayersAtAllTablesToZero() {

        List<GameTable> gameTables = gameTableService.findAllGameTables();
        for (GameTable element : gameTables) {
            element.setPlayerRedFirstId(0l);
            element.setPlayerRedSecondId(0l);
            element.setPlayerBlueFirstId(0l);
            element.setPlayerBlueSecondId(0l);
        }

    }

}
