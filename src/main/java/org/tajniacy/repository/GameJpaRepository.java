package org.tajniacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tajniacy.model.Game;
import org.tajniacy.model.GameTable;

public interface GameJpaRepository extends JpaRepository<Game, Long> {

    Game findGameByGameTable_NameIgnoreCase(String gameTableName);

    Game findById(Long gameId);

    void deleteGameByGameTable(GameTable gameTable);

    void deleteGameByGameTable_NameIgnoreCase(String gameTableName);

}
