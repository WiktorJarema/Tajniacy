//package org.tajniacy.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tajniacy.exception.NicknameNotFoundException;
//import org.tajniacy.model.Game;
//import org.tajniacy.model.GameTable;
//import org.tajniacy.model.GameWord;
//import org.tajniacy.model.Nickname;
//import org.tajniacy.repository.GameJpaRepository;
//import org.tajniacy.repository.GameTableJpaRepository;
//import org.tajniacy.repository.GameWordJpaRepository;
//import org.tajniacy.service.GameService;
//import org.tajniacy.service.GameTableService;
//import org.tajniacy.service.NicknameService;
//
//import javax.servlet.http.HttpSession;
//import javax.transaction.Transactional;
//
//@Controller
//@Transactional
//public class HomeControllerPop {
//
//    // to tymczasowo ma być, do usunięcia
//    @Autowired
//    GameJpaRepository gameJpaRepository;
//
//    // to tymczasowo ma być, do usunięcia
//    @Autowired
//    GameTableJpaRepository gameTableJpaRepository;
//
//    // to tymczasowo ma być, do usunięcia
//    @Autowired
//    GameWordJpaRepository gameWordJpaRepository;
//
//    @Autowired
//    NicknameService nicknameService;
//
//    @Autowired
//    GameTableService gameTableService;
//
//    @Autowired
//    GameService gameService;
//
//    @GetMapping(path = "/start", produces = "text/html; charset=UTF-8")
//    @ResponseBody
//    public String start() {
//        System.out.println("początek metody start");
//
////        gameTableService.createNewTable("Copacabana");
//
////        GameTable gameTable = gameTableService.findGameTableByName("Copacabana");
////        Game createGame = gameService.createNewGame(gameTable);
////        gameTable.setGame(createGame);
////        System.out.println(gameTable);
////        System.out.println(createGame);
//
//        // 1 stół
//        GameTable gameTable = new GameTable("Copacabana");
//        Game game = new Game(9l, 8l, "redTeamSeat1");
//        gameTable.setGame(game);
//        gameTableJpaRepository.save(gameTable);
//        gameJpaRepository.save(game);
//
//        // 2 stół
//        gameTable = new GameTable("Jurata");
//        game = new Game(9l, 8l, "redTeamSeat1");
//        gameTable.setGame(game);
//        gameTableJpaRepository.save(gameTable);
//        gameJpaRepository.save(game);
//
//        // 3 stół
//        gameTable = new GameTable("Sicilia");
//        game = new Game(9l, 8l, "redTeamSeat1");
//        gameTable.setGame(game);
//        gameTableJpaRepository.save(gameTable);
//        gameJpaRepository.save(game);
//
//
////        gameTableJpaRepository.save(gameTable);
////        gameJpaRepository.save(createGame);
//
//
//        System.out.println("koniec metody start");
//
//        return "jest ok";
//    }
//
//
//    @GetMapping(path = "/startek", produces = "text/html; charset=UTF-8")
//    @ResponseBody
//    public String startek() {
//
//        gameTableService.createNewTable("Copacabana");
//
//        GameTable gameTable = gameTableService.findGameTableByName("Copacabana");
//        Game game = gameService.createNewGame(gameTable);
//        gameTable.setGame(game);
//        gameService.saveGame(game);
//
//
//        // 3 stoły
//        gameTable = new GameTable("Majorka");
//        gameTableJpaRepository.save(gameTable);
//        gameTable = new GameTable("Jurata");
//        gameTableJpaRepository.save(gameTable);
//        gameTable = new GameTable("Sicilia");
//        gameTableJpaRepository.save(gameTable);
//
//
//        gameTable = gameTableJpaRepository.findByNameIgnoreCase("sicilia");
//        game = new Game(9l, 8l, "redTeamSeat1");
//        gameTable.setGame(game);
//        gameJpaRepository.save(game);
//
//
//        gameTable = gameTableJpaRepository.findByNameIgnoreCase("jurata");
//        gameTable.setGame(new Game(9l, 8l, "heheh"));
//        gameTableJpaRepository.save(gameTable);
//
//
//
//        return "jest ok";
//    }
//
//
//    @GetMapping(path = "/test", produces = "text/html; charset=UTF-8")
//    @ResponseBody
//    public String test() {
//        System.out.println("początek metody start");
//
////        gameTableService.createNewTable("Copacabana");
//
////        GameTable gameTable = gameTableService.findGameTableByName("Copacabana");
////        Game createGame = gameService.createNewGame(gameTable);
////        gameTable.setGame(createGame);
////        System.out.println(gameTable);
////        System.out.println(createGame);
//
//
//
////        GameTable gameTable = gameTableJpaRepository.findOne(1l);
//        Game game = gameJpaRepository.findGameByGameTable_NameIgnoreCase("jurata");
////        gameTable.setPlayerRedSecondId(4l);
//        game.setPlayerTurnName("hej");
//
//
//        game.addGameWordToGame(new GameWord("słowo1", "red"));
//        game.addGameWordToGame(new GameWord("miś", "red"));
//        game.addGameWordToGame(new GameWord("akcja", "red"));
//
//
////        gameWordJpaRepository.save(gameWord);
//        gameJpaRepository.save(game);
////        gameTableJpaRepository.save(gameTable);
//
//
//
//
////        gameTableJpaRepository.save(gameTable);
////        gameJpaRepository.save(createGame);
//
//
//
//        System.out.println("koniec metody start");
//
//        return "jest ok";
//    }
//
//    @GetMapping(path = "/home", produces = "text/html; charset=UTF-8")
//    public String homePage(HttpSession session, Model model) throws NicknameNotFoundException {
//
//
//        // kod zerujący graczy w gameTable
////        if (nicknameService.getNicknameById(1l).getIsFree() == true) {
////            gameTableService.clearPlayersAtGameTables();
////            System.out.println("gracze przy stołach wyzerowani");
////        }
//
//        // właściwa treść tej metody
//        Object nicknameObject = session.getAttribute("nickname");
//        if (nicknameObject == null) {
//            Nickname newNickname = nicknameService.getNextFreeNickname();
//            session.setAttribute("nickname", newNickname);
//            session.setMaxInactiveInterval(0);
//
//            model.addAttribute("welcomeMessage", "Cześć, to będzie Twój nick: " + newNickname.getName());
//            return "index";
//        } else {
//            Nickname usedNickname = (Nickname) nicknameObject;
//            model.addAttribute("welcomeMessage", "A my się już znamy, cześć " + usedNickname.getName());
//            return "index";
//        }
//
//    }
//
//    @GetMapping(path = "/{gameTableName}")
//    public String getGameTable(@PathVariable(name = "gameTableName") String gameTableName,
//                                   HttpSession session,
//                                   Model model) throws NicknameNotFoundException {
//
//        Object nicknameObject = session.getAttribute("nickname");
//        if (nicknameObject == null) {
//            return "redirect:http://google.pl";
//        } else {
//            model.addAttribute("gameTable", gameTableService.findGameTableByName(gameTableName).getName());
//
//            Nickname usedNickname = (Nickname) nicknameObject;
//            Long playerId = usedNickname.getId();
//            System.out.println(playerId);
//            gameTableService.addPlayerToGameTable(gameTableName, playerId);
//
//            model.addAttribute("redTeamSeat1", gameTableService.getPlayerNameFromRedTeamSeat1(gameTableName));
//            model.addAttribute("redTeamSeat2", gameTableService.getPlayerNameFromRedTeamSeat2(gameTableName));
//            model.addAttribute("blueTeamSeat1", gameTableService.getPlayerNameFromBlueTeamSeat1(gameTableName));
//            model.addAttribute("blueTeamSeat2", gameTableService.getPlayerNameFromBlueTeamSeat2(gameTableName));
//
//            return "game-table";
//        }
//
//    }
//
////    @GetMapping(path = "/", produces = "text/html; charset=UTF-8")
////    public String test() {
////
////        return "index";
////    }
//
//}
