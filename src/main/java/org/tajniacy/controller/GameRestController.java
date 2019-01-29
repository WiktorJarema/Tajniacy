package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tajniacy.model.Game;
import org.tajniacy.model.Word;
import org.tajniacy.service.GameService;

import java.util.List;

// na razie z tego controllea nie będą korzystał, wszystko w GameTableRestController
@RestController
public class GameRestController {

    @Autowired
    private GameService gameService;

//    @GetMapping(path = "/getwords/{numberOfWords}/{language}") // tu raczej docelowo będzie PostMapping
//    public List<Word> getRandomWords(@PathVariable(name = "numberOfWords") int numberOfWords,
//                                     @PathVariable(name = "language") String language) {
//
//        return gameService.findNRandomWordsByLanguage(numberOfWords, language);
//
//    }
//
//    @GetMapping(path = "/getwords")
//    public List<Word> getRandomWords() {
//
//        return gameService.find25RandomWords();
//
//    }

//    @GetMapping(path = "/{gameTableName}/newgame")
//    public Game createNewGame(@PathVariable(name = "gameTableName") String gameTableName) {
//
//        return gameService.createNewGame(gameTableName);
//
//    }
}
