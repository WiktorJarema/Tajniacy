package org.tajniacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tajniacy.model.Game;

public interface GameJpaRepository extends JpaRepository<Game, Long> {

    Game findGameByGameTable_NameIgnoreCase(String gameTableName);


}
