package org.tajniacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tajniacy.model.Word;

import java.util.List;

public interface WordJpaRepository extends JpaRepository<Word, Long> {

    @Query(value = "SELECT * FROM dictionary WHERE language=\"pl\" ORDER BY RAND() limit 25", nativeQuery = true)
    List<Word> find25RandomWords();

    @Query(value = "SELECT * FROM dictionary WHERE language=?2 ORDER BY RAND() limit ?1", nativeQuery = true)
    List<Word> findNRandomWordsByLanguage(int numberOfWords, String language);


}
