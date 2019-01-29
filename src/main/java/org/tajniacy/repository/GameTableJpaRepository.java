package org.tajniacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tajniacy.model.GameTable;

import java.util.List;

public interface GameTableJpaRepository extends JpaRepository<GameTable, Long> {

    GameTable findByNameIgnoreCase(String gameTableName);

    GameTable findById(Long gameTableId);



}
