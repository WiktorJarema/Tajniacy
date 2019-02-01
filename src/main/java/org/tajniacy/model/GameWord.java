package org.tajniacy.model;

import javax.persistence.*;

@Entity
@Table(name = "game_words")
public class GameWord implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_word_id")
    private Long id;

    private String word;

    // zmienić na cardType albo wordType
    @Column(name = "team_colour")
    private String teamColour;

    @Column(name = "is_hit")
    private boolean isHit;

    @ManyToOne//(fetch = FetchType.LAZY) - tego podobno ma niebyć, przez analogię do publisher i book na zajęciach
    @JoinColumn(name = "game_id")
    private Game game;

    public GameWord() {
    }

    public GameWord(/*Game game, */String word, String teamColour) {
//        this.game = game;
        this.word = word;
        this.teamColour = teamColour;
        this.isHit = false;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(String teamColour) {
        this.teamColour = teamColour;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean hit) {
        isHit = hit;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public GameWord clone() {
        GameWord clone = null;
        try {
            clone = (GameWord) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
