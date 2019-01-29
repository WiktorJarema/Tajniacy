package org.tajniacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tajniacy.model.Game;
import org.tajniacy.model.GameTable;
import org.tajniacy.model.Word;
import org.tajniacy.repository.GameJpaRepository;
import org.tajniacy.repository.GameTableJpaRepository;
import org.tajniacy.repository.WordJpaRepository;
import org.tajniacy.service.GameService;
import org.tajniacy.service.GameWordService;
import org.tajniacy.service.WordService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
//@Transactional // próbuję z, może to pomoże żeby gra się zapisywała
public class GameServiceImpl implements GameService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    // to tymczasowo ma być, do usunięcia
    @Autowired
    GameTableJpaRepository gameTableJpaRepository;

    @Autowired
    private GameWordService gameWordService;

    @Autowired
    private WordService wordService;

    // niew wiem czy zapisywać grę za pomocą servisu czy bezpośrednio za pomoca repozytorium
    @Override
    public void saveGame(Game game) {
        gameJpaRepository.save(game);
    }

    @Override
    public void deleteGame(Game game) {
        gameJpaRepository.delete(game);
    }

    // to do wyrzucenia, zrobiłem dla testów
//    public void createNewGame() {
//        Game game = new Game();
//        System.out.println(game);
//        gameJpaRepository.save(game);
//    }

    @Override
    public Game createNewGame(GameTable gameTable) {
        Game game = new Game(9l, 8l, "redTeamSeat1");
//        Game game = new Game();
//        System.out.println(game);
        System.out.println("wewnątrz createNewGame w GameService");
//        gameTable.setGame(game);
        System.out.println(game);
//        gameTableJpaRepository.save(gameTable);
//        gameJpaRepository.save(game);
//        gameJpaRepository.save(game);
        System.out.println("wewnątrz createNewGame w GameService po zapisie");
//        List<Word> words = wordService.get25RandomWords();
//        for (Word element : words) {
//            System.out.println(element.getWord());
//        }
//        System.out.println(words.size());
//
//        List<String> cardTypes = new ArrayList<>();
//
//        for (int i = 0; i < 8; i++) {
//            cardTypes.add("red");
//            System.out.println("utwórz nową kartę");
//            cardTypes.add("blue");
//            cardTypes.add("neutral");
//        }
//        cardTypes.add("red");
//        for (String element : cardTypes) {
//            System.out.println(element);
//        }
//        Collections.shuffle(cardTypes);
//
//        int test = 0;
//        for (int i = 0; i < 25; i++) {
//            System.out.println("Utwórz nowe słowo");
//            gameWordService.createNewGameWord(game, words.get(i).getWord(), cardTypes.get(i));
//            test = i;
//        }
//        System.out.println("wartość test: " + test);
//
//        gameJpaRepository.save(game);

        return game;
    }


}
