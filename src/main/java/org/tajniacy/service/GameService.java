package org.tajniacy.service;

import org.tajniacy.model.*;

import java.util.List;

public interface GameService {

    void saveGame(Game game);

    void deleteGame(Game game);

    void deleteGameByGameTableName(String gameTableName);

    void deleteGameByGameTable(GameTable gameTable);

    Game createNewGame(GameTable gameTable);

    void createNewGameWords(GameTable gameTable);

    List<Game> findAllGames();

    Game findGameById(Long gameId);

    Game findGameByGameTable(GameTable gameTable);

    Game findGameByGameTableName(String gameTableName);

    void turnChange(String gameTableName);

    boolean checkIfItIsHisTurn(String gameTableName, Nickname nickname);

    String getPlayersCurrentSeatName(String gameTableName, Nickname nickname);
}
