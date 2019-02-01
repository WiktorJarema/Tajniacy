package org.tajniacy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // niepotrzebne, bo korzystam z MapsId
//    @Column(name = "game_id")
    private Long id;

    @JsonIgnore
    @OneToOne//(fetch = FetchType.LAZY)
//    @JoinColumn(name = "game_id") // to jak włącze to nie działa
//    @Column(name = "game_id") // to jak włącze to nie działa
//    @JoinColumn(name = "subscriptionId", referencedColumnName = "id") // to też nie działa
    @MapsId
    private GameTable gameTable;

    @Column(name = "red_team_words_left", columnDefinition = "TINYINT UNSIGNED")
    private Long redTeamWordsLeft;

    @Column(name = "blue_team_words_left", columnDefinition = "TINYINT UNSIGNED")
    private Long blueTeamWordsLeft;

    // tu zamienić później typ na enum
    @Column(name = "player_turn_name")
    private String playerTurnName;

    @JsonIgnore
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<GameWord> gameWords = new ArrayList<>();


    public Game() {
    }

    public Game(Long redTeamWordsLeft, Long blueTeamWordsLeft, String playerTurnName) {
        this.redTeamWordsLeft = redTeamWordsLeft;
        this.blueTeamWordsLeft = blueTeamWordsLeft;
        this.playerTurnName = playerTurnName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRedTeamWordsLeft() {
        return redTeamWordsLeft;
    }

    public void setRedTeamWordsLeft(Long redTeamWordsLeft) {
        this.redTeamWordsLeft = redTeamWordsLeft;
    }

    public Long getBlueTeamWordsLeft() {
        return blueTeamWordsLeft;
    }

    public void setBlueTeamWordsLeft(Long blueTeamWordsLeft) {
        this.blueTeamWordsLeft = blueTeamWordsLeft;
    }

    public String getPlayerTurnName() {
        return playerTurnName;
    }

    public void setPlayerTurnName(String playerTurnName) {
        this.playerTurnName = playerTurnName;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public List<GameWord> getGameWords() {
        return gameWords;
    }

    public void setGameWords(List<GameWord> gameWords) {
        this.gameWords = gameWords;
    }

    public void addGameWordToGame(GameWord gameWord) {
        this.gameWords.add(gameWord);
        gameWord.setGame(this);
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +

                ", redTeamWordsLeft=" + redTeamWordsLeft +
                ", blueTeamWordsLeft=" + blueTeamWordsLeft +
                ", playerTurnName='" + playerTurnName + '\'' +
                '}';
    }
}
