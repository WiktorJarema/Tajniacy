package org.tajniacy.service;

import org.tajniacy.model.Game;
import org.tajniacy.model.GameTable;
import org.tajniacy.model.Word;

import java.util.List;

public interface GameService {

    void saveGame(Game game);

    void deleteGame(Game game);

    Game createNewGame(GameTable gameTable);
}
