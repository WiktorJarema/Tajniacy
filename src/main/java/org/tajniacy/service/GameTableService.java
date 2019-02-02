package org.tajniacy.service;

import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Game;
import org.tajniacy.model.GameTable;
import org.tajniacy.repository.GameTableJpaRepository;

import java.util.List;

public interface GameTableService {

    GameTable findGameTableByName(String gameTableName);

    GameTable findGameTableById(Long gameTableId);

    List<GameTable> findAllGameTables();

    void addPlayerToGameTable(String gameTableName, Long seatNumber, Long playerId);

    void addPlayerToGameTable(String gameTableName, Long playerId);

    // metoda na potrzeby testów, wykorzystywana w home na starcie
//    void clearPlayersAtGameTables();

    Long getPlayerIdFromRedTeamSeat1(String gameTableName);

    String getPlayerNameFromRedTeamSeat1(String gameTableName) throws NicknameNotFoundException;

    String getPlayerNameFromRedTeamSeat2(String gameTableName) throws NicknameNotFoundException;

    String getPlayerNameFromBlueTeamSeat1(String gameTableName) throws NicknameNotFoundException;

    String getPlayerNameFromBlueTeamSeat2(String gameTableName) throws NicknameNotFoundException;

    String getWhoseTurnSeatName(String gameTableName);

    Game createNewGame(GameTable gameTable);

    void createNewTable(String gameTableName);

    void deleteGame(String gameTableName);
}
