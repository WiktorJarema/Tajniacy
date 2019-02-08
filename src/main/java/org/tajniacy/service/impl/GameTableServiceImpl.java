package org.tajniacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Game;
import org.tajniacy.model.GameTable;
import org.tajniacy.repository.GameTableJpaRepository;
import org.tajniacy.repository.NicknameRepository;
import org.tajniacy.service.GameService;
import org.tajniacy.service.GameTableService;

import javax.transaction.Transactional;
import java.util.List;

@Service
//@Transactional // próbuję z, może to pomoże żeby gra się zapisywała
public class GameTableServiceImpl implements GameTableService  {

    @Autowired
    private GameTableJpaRepository gameTableJpaRepository;

    @Autowired
    private NicknameRepository nicknameRepository;

    @Autowired
    private GameService gameService;

    @Override
    public GameTable findGameTableByName(String gameTableName) {
        return gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
    }

    @Override
    public GameTable findGameTableById(Long gameTableId) {
        return gameTableJpaRepository.findById(gameTableId);
    }

    @Override
    public List<GameTable> findAllGameTables() {
        return gameTableJpaRepository.findAll();
    }

    // z tego nie korzystam, to na później - teraz próbuję czy działa
    @Override
    public void addPlayerToGameTable(String gameTableName, Long seatId, Long playerId) {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);

        if (gameTable.getPlayerRedFirstId() != playerId &&
                gameTable.getPlayerRedSecondId() != playerId &&
                gameTable.getPlayerBlueFirstId() != playerId &&
                gameTable.getPlayerBlueSecondId() != playerId) {
            if (seatId == 1 && gameTable.getPlayerRedFirstId() == 0) {
                gameTable.setPlayerRedFirstId(playerId);
            } else if (seatId == 2 && gameTable.getPlayerRedSecondId() == 0) {
                gameTable.setPlayerRedSecondId(playerId);
            } else if (seatId == 3 && gameTable.getPlayerBlueFirstId() == 0) {
                gameTable.setPlayerBlueFirstId(playerId);
            } else if (seatId == 4 && gameTable.getPlayerBlueSecondId() == 0) {
                gameTable.setPlayerBlueSecondId(playerId);
            } else {
                System.out.println("coś nie tak z dodawaniem gracza do stołu");
                System.out.println(gameTable.getPlayerRedFirstId());
                System.out.println(gameTable.getPlayerRedSecondId());
                System.out.println(gameTable.getPlayerBlueFirstId());
                System.out.println(gameTable.getPlayerBlueSecondId());
            }
            gameTableJpaRepository.save(gameTable);

        } else {
            System.out.println("Gracz już jest na stole.");
        }

    }

    // tego już nie używam, to było tylko na czas jak nie było samodzielnego zajmowania stołu
//    @Override
//    public void addPlayerToGameTable(String gameTableName, Long playerId) {
//        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
//
//        if (gameTable.getPlayerRedFirstId() != playerId &&
//                gameTable.getPlayerRedSecondId() != playerId &&
//                gameTable.getPlayerBlueFirstId() != playerId &&
//                gameTable.getPlayerBlueSecondId() != playerId) {
//
//            if (gameTable.getPlayerRedFirstId() == 0) {
//                gameTable.setPlayerRedFirstId(playerId);
//            } else if (gameTable.getPlayerRedSecondId() == 0) {
//                gameTable.setPlayerRedSecondId(playerId);
//            } else if (gameTable.getPlayerBlueFirstId()== 0) {
//                gameTable.setPlayerBlueFirstId(playerId);
//            } else if (gameTable.getPlayerBlueSecondId() == 0) {
//                gameTable.setPlayerBlueSecondId(playerId);
//            } else {
//                System.out.println("coś nie tak");
//            }
//            gameTableJpaRepository.save(gameTable);
//
//
//        } else {
//            System.out.println("Gracz już jest na stole");
//        }
//
//    }

    @Override
    public void deletePlayerFromGameTable(String gameTableName, Long seatId, Long playerId) {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);

        if (seatId == 1 && gameTable.getPlayerRedFirstId() == playerId) {
            gameTable.setPlayerRedFirstId(0l);
        } else if (seatId == 2 && gameTable.getPlayerRedSecondId() == playerId) {
            gameTable.setPlayerRedSecondId(0l);
        } else if (seatId == 3 && gameTable.getPlayerBlueFirstId() == playerId) {
            gameTable.setPlayerBlueFirstId(0l);
        } else if (seatId == 4 && gameTable.getPlayerBlueSecondId() == playerId) {
            gameTable.setPlayerBlueSecondId(0l);
        } else {
            System.out.println("coś nie tak");
        }
        gameTableJpaRepository.save(gameTable);
    }

//    @Override
//    public void clearPlayersAtGameTables() {
//        GameTable gameTable;
//
//        for (Long i = 1l; i <= 6L; i++) {
//            gameTable = gameTableJpaRepository.findById(i);
//            gameTable.setPlayerRedFirstId(0l);
//            gameTable.setPlayerRedSecondId(0l);
//            gameTable.setPlayerBlueFirstId(0l);
//            gameTable.setPlayerBlueSecondId(0l);
//            gameTableJpaRepository.save(gameTable);
//        }
//
//    }

    // te metody getPlayerIdFromRedTeamSeat itd trzeba by scalić w jedną
    @Override
    public Long getPlayerIdFromRedTeamSeat1(String gameTableName) {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        return gameTable.getPlayerRedFirstId();
    }

    @Override
    public String getPlayerNameFromRedTeamSeat1(String gameTableName) throws NicknameNotFoundException {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        Long playerId = gameTable.getPlayerRedFirstId();
        if (playerId == 0) {
            return "wolne miejsce";
        } else {
            return nicknameRepository.findNicknameById(playerId).getName();
        }

    }

    @Override
    public String getPlayerNameFromRedTeamSeat2(String gameTableName) throws NicknameNotFoundException {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        Long playerId = gameTable.getPlayerRedSecondId();
        if (playerId == 0) {
            return "wolne miejsce";
        } else {
            return nicknameRepository.findNicknameById(playerId).getName();
        }
    }

    @Override
    public String getPlayerNameFromBlueTeamSeat1(String gameTableName) throws NicknameNotFoundException {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        Long playerId = gameTable.getPlayerBlueFirstId();
        if (playerId == 0) {
            return "wolne miejsce";
        } else {
            return nicknameRepository.findNicknameById(playerId).getName();
        }
    }

    @Override
    public String getPlayerNameFromBlueTeamSeat2(String gameTableName) throws NicknameNotFoundException {
        GameTable gameTable = gameTableJpaRepository.findByNameIgnoreCase(gameTableName);
        Long playerId = gameTable.getPlayerBlueSecondId();
        if (playerId == 0) {
            return "wolne miejsce";
        } else {
            return nicknameRepository.findNicknameById(playerId).getName();
        }
    }

    @Override
    public String getWhoseTurnSeatName(String gameTableName) {
        Game game = gameService.findGameByGameTableName(gameTableName);

        // decyduje się na zwracanie nazwy miejsca, nie numeru
//        switch(game.getPlayerTurnName()) {
//            case "redTeamSeat1":
//                return 1;
//            case "redTeamSeat2":
//                return 2;
//            case "blueTeamSeat1":
//                return 3;
//            case "blueTeamSeat2":
//                return 4;
//        }
        return game.getPlayerTurnName();
    }

    @Override
    public Game createNewGame(GameTable gameTable) {
        // nie działa mi usuwanie a potem tworzenie nowego stołu, nie wiem dlaczego
//        gameService.deleteGameByGameTable(gameTable);
//        gameService.deleteGameByGameTableName(gameTable.getName());
        Game game = gameService.createNewGame(gameTable);
        return game;
    }

    // z kontrolera odwołuję się od razu do gameService
//    @Override
//    public void createNewGameWords(GameTable gameTable) {
//        gameService.createNewGameWords(gameTable);
//    }

    @Override
    public void createNewTable(String gameTableName) {
        GameTable gameTable = new GameTable(gameTableName);
        gameTableJpaRepository.save(gameTable);
    }

    @Override
    public void deleteGame(String gameTableName) {
        gameService.deleteGameByGameTableName(gameTableName);
    }
}
