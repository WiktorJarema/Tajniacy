package org.tajniacy.service;

import org.tajniacy.model.Game;
import org.tajniacy.model.GameTable;
import org.tajniacy.model.GameWord;
import org.tajniacy.model.Word;

import java.util.List;

public interface GameService {

    void saveGame(Game game);

    void deleteGame(Game game);

    void deleteGameByGameTableName(String gameTableName);

    void deleteGameByGameTable(GameTable gameTable);

    Game createNewGame(GameTable gameTable);

    List<Game> findAllGames();

    Game findGameById(Long gameId);

    Game findGameByGameTable(GameTable gameTable);

    Game findGameByGameTableName(String gameTableName);

    void turnChange(String gameTableName);

}
