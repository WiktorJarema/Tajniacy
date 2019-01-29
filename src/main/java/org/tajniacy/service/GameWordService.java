package org.tajniacy.service;

import org.tajniacy.model.Game;
import org.tajniacy.model.Word;

public interface GameWordService {

    void createNewGameWord(Game game, String word, String teamColor);

}
