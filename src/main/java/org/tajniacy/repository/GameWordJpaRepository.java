package org.tajniacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tajniacy.model.Game;
import org.tajniacy.model.GameWord;

public interface GameWordJpaRepository extends JpaRepository<GameWord, Long> {


}
