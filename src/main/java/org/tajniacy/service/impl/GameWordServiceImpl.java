package org.tajniacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tajniacy.model.Game;
import org.tajniacy.model.GameWord;
import org.tajniacy.model.Word;
import org.tajniacy.repository.GameWordJpaRepository;
import org.tajniacy.service.GameWordService;

@Service
public class GameWordServiceImpl implements GameWordService {

    @Autowired
    private GameWordJpaRepository gameWordJpaRepository;

    @Override
    public void createNewGameWord(Game game, String word, String teamColor) {
//        GameWord gameWord = new GameWord(game, word, teamColor);
        GameWord gameWord = new GameWord();
        System.out.println("czy tu ok?");
        System.out.println(gameWord);
        gameWordJpaRepository.save(gameWord);
        System.out.println("a jak tu?");
    }

}
