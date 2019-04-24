package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
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
    public String test(HttpSession session) {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject == null) {
            return "nicknameObject jest null";
        } else {
            Nickname usedNickname = (Nickname) nicknameObject;
            return "Twój nickname to: " + usedNickname.getName();
        }
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


    @GetMapping(path = "/", produces = "text/html; charset=UTF-8")
    public String homePage(HttpSession session, Model model) throws NicknameNotFoundException {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject == null) {
            Nickname newNickname = nicknameService.getNextFreeNickname();
            session.setAttribute("nickname", newNickname);
            session.setMaxInactiveInterval(10);

//            model.addAttribute("welcomeMessage", "Cześć, to będzie Twój nick: " + newNickname.getName());
            model.addAttribute("welcomeMessage1", "Witaj na stronie poświęconej popularnej grze \"Tajniacy\".");
            model.addAttribute("welcomeMessage2", "Aby zagrać wybierz jeden z dostępnych stołów.");
            model.addAttribute("welcomeMessage3", "Na potrzeby gry został Ci przydzielony pseudonim:");
            model.addAttribute("nickname_name", newNickname.getName());
            model.addAttribute("infoMessage1", "Strona funkcjonuje od niedawna i podlega ciągłemu rozwojowi.");
            model.addAttribute("infoMessage2", "Projekt w fazie proof of concept, także wybacz, jeżeli coś nie zadziała;)");
            model.addAttribute("cookiesMessage", "Strona używa plików cookies (tzw. ciasteczka) w celach funkcjonalnych.");
            return "index";
        } else {
            Nickname usedNickname = (Nickname) nicknameObject;
            model.addAttribute("welcomeMessage3", "Cześć " + usedNickname.getName() + ", my się już znamy.");
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
