package org.tajniacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tajniacy.model.*;
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
        GameTable gameTable = gameTableJpaRepository.findByGame(game);
        gameTable.deleteGameFromGameTable(game);
        gameJpaRepository.delete(game);
    }

    @Override
    public void deleteGameByGameTableName(String gameTableName) {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        gameTable.deleteGameFromGameTable(gameTable.getGame());
        gameJpaRepository.deleteGameByGameTable_NameIgnoreCase(gameTableName);
    }

    @Override
    public void deleteGameByGameTable(GameTable gameTable) {
        gameTable.deleteGameFromGameTable(gameTable.getGame());
        gameJpaRepository.deleteGameByGameTable(gameTable);
    }

    @Override
    public Game createNewGame(GameTable gameTable) {
        Game game = new Game(9l, 8l, "redTeamSeat1");
        gameTable.setGame(game);

        List<Word> words = wordService.get25RandomWords();
        for (Word element : words) {
            System.out.println(element.getWord());
        }

        List<String> cardTypes = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            cardTypes.add("red");
            cardTypes.add("blue");
            cardTypes.add("neutral");
        }
        cardTypes.add("red");

        for (String element : cardTypes) {
            System.out.println(element);
        }
        Collections.shuffle(cardTypes);

        for (int i = 0; i < 25; i++) {
            gameWordService.createNewGameWord(game, words.get(i).getWord(), cardTypes.get(i));
        }

        gameJpaRepository.save(game);

        return game;
    }

    @Override
    public void createNewGameWords(GameTable gameTable) {
        Game game = gameTable.getGame();
        game.setPlayerTurnName("redTeamSeat1");
        game.setClueWord("");

//        System.out.println("czyszczenie słów");

        List<GameWord> currentWords = game.getGameWords();
        currentWords.clear();


        List<Word> words = wordService.get25RandomWords();
        for (Word element : words) {
            System.out.println(element.getWord());
        }

        List<String> cardTypes = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            cardTypes.add("red");
            cardTypes.add("blue");
            cardTypes.add("neutral");
        }
        cardTypes.add("red");

        for (String element : cardTypes) {
            System.out.println(element);
        }
        Collections.shuffle(cardTypes);

        for (int i = 0; i < 25; i++) {
            gameWordService.createNewGameWord(game, words.get(i).getWord(), cardTypes.get(i));
        }

//        gameJpaRepository.save(game);

    }

    @Override
    public List<Game> findAllGames() {
        return gameJpaRepository.findAll();
    }

    @Override
    public Game findGameById(Long gameId) {
        return gameJpaRepository.findById(gameId);
    }

    @Override
    public Game findGameByGameTable(GameTable gameTable) {
        return gameJpaRepository.findGameByGameTable_NameIgnoreCase(gameTable.getName());
    }

    @Override
    public Game findGameByGameTableName(String gameTableName) {
        return gameJpaRepository.findGameByGameTable_NameIgnoreCase(gameTableName);
    }

    // zwracam nazwę miejsce którego jest teraz tura
    @Override
    public void turnChange(String gameTableName) {

        Game game = gameJpaRepository.findGameByGameTable_NameIgnoreCase(gameTableName);
        String currentWhoseTurn = game.getPlayerTurnName();

        switch (currentWhoseTurn) {
            case "redTeamSeat1":
                game.setPlayerTurnName("redTeamSeat2");
                break;
            case "redTeamSeat2":
                game.setPlayerTurnName("blueTeamSeat1");
                break;
            case "blueTeamSeat1":
                game.setPlayerTurnName("blueTeamSeat2");
                break;
            case "blueTeamSeat2":
                game.setPlayerTurnName("redTeamSeat1");
                break;
        }

    }

    @Override
    public boolean checkIfItIsHisTurn(String gameTableName, Nickname nickname) {

        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        Game game = gameJpaRepository.findGameByGameTable_NameIgnoreCase(gameTableName);

        String playerSeat = null;
        String currentWhoseTurn = game.getPlayerTurnName();
        Long playerId = nickname.getId();

        System.out.println("playerId " + playerId);
        System.out.println("playerId " + playerId);

        if (playerId.compareTo(gameTable.getPlayerRedFirstId()) == 0) {
            playerSeat = "redTeamSeat1";
        } else if (playerId.compareTo(gameTable.getPlayerRedSecondId()) == 0) {
            playerSeat = "redTeamSeat2";
        } else if (playerId.compareTo(gameTable.getPlayerBlueFirstId()) == 0) {
            playerSeat = "blueTeamSeat1";
        } else if (playerId.compareTo(gameTable.getPlayerBlueSecondId()) == 0) {
            playerSeat = "blueTeamSeat2";
        } else {
            System.out.println("ids się nie zgadzają");
            return false;
        }

        System.out.println("players seat: " + playerSeat + " , teraz kolej: " + currentWhoseTurn);

        if (playerSeat.equals(currentWhoseTurn)) {
            System.out.println("tuttt");
            return true;
        } else {
            System.out.println("hheh");
            return false;
        }


    }

    @Override
    public String getPlayersCurrentSeatName(String gameTableName, Nickname nickname) {

        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        Long playerId = nickname.getId();

        if (playerId.compareTo(gameTable.getPlayerRedFirstId()) == 0) {
            return "redTeamSeat1";
        } else if (playerId.compareTo(gameTable.getPlayerRedSecondId()) == 0) {
            return  "redTeamSeat2";
        } else if (playerId.compareTo(gameTable.getPlayerBlueFirstId()) == 0) {
            return  "blueTeamSeat1";
        } else if (playerId.compareTo(gameTable.getPlayerBlueSecondId()) == 0) {
            return  "blueTeamSeat2";
        } else {
            return null;
        }

    }


}
