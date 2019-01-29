package org.tajniacy.model;

import javax.persistence.*;

@Entity
@Table(name = "dictionary")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "language")
    private String language;

    private String category;

    @Column(name = "difficulty_level", columnDefinition = "TINYINT UNSIGNED")
    private Long difficultyLevel;


    public Word() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Long difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

}
