package org.tajniacy.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;

@Entity
@Table(name = "game_tables")
public class GameTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_table_id")
    private Long id;

//    @NotEmpty
    private String name;

    @Column(name = "player_red_1_id")
    private Long playerRedFirstId;

    @Column(name = "player_red_2_id")
    private Long playerRedSecondId;

    @Column(name = "player_blue_1_id")
    private Long playerBlueFirstId;

    @Column(name = "player_blue_2_id")
    private Long playerBlueSecondId;

    @Column(name = "number_of_words", columnDefinition = "TINYINT UNSIGNED")
    //@NotEmpty na razie nie daje, ale powinno być
    private Long numberOfWords;

    @Column(name = "difficulty_level", columnDefinition = "TINYINT UNSIGNED")
    //@NotEmpty na razie nie daje, ale powinno być
    private Long difficultyLevel;

    @Column(name = "starting_team_number_of_words", columnDefinition = "TINYINT UNSIGNED")
    //@NotEmpty na razie nie daje, ale powinno być
    private Long startingTeamNumberOfWords;

    // wg artykułu najlepszy mapping OneToOne jest unidirectional, dlatego odkomentowany getGame i setGame - na końcu
    @OneToOne(mappedBy = "gameTable",
            cascade = CascadeType.ALL/*,
            fetch = FetchType.LAZY*/)
    private Game game;


    public GameTable() {
    }

    public GameTable(String name) {
        this.name = name;
        this.playerRedFirstId = 0l;
        this.playerRedSecondId = 0l;
        this.playerBlueFirstId = 0l;
        this.playerBlueSecondId = 0l;
        this.numberOfWords = 25l;
        this.difficultyLevel = 0l;
        this.startingTeamNumberOfWords = 9l;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlayerRedFirstId() {
        return playerRedFirstId;
    }

    public void setPlayerRedFirstId(Long playerRedFirstId) {
        this.playerRedFirstId = playerRedFirstId;
    }

    public Long getPlayerRedSecondId() {
        return playerRedSecondId;
    }

    public void setPlayerRedSecondId(Long playerRedSecondId) {
        this.playerRedSecondId = playerRedSecondId;
    }

    public Long getPlayerBlueFirstId() {
        return playerBlueFirstId;
    }

    public void setPlayerBlueFirstId(Long playerBlueFirstId) {
        this.playerBlueFirstId = playerBlueFirstId;
    }

    public Long getPlayerBlueSecondId() {
        return playerBlueSecondId;
    }

    public void setPlayerBlueSecondId(Long playerBlueSecondtId) {
        this.playerBlueSecondId = playerBlueSecondtId;
    }

    public Long getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(Long numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public Long getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Long difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Long getStartingTeamNumberOfWords() {
        return startingTeamNumberOfWords;
    }

    public void setStartingTeamNumberOfWords(Long startingTeamNumberOfWords) {
        this.startingTeamNumberOfWords = startingTeamNumberOfWords;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        game.setGameTable(this);
    }

    @Override
    public String toString() {
        return "GameTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playerRedFirstId=" + playerRedFirstId +
                ", playerRedSecondId=" + playerRedSecondId +
                ", playerBlueFirstId=" + playerBlueFirstId +
                ", playerBlueSecondId=" + playerBlueSecondId +
                ", numberOfWords=" + numberOfWords +
                ", difficultyLevel=" + difficultyLevel +
                ", startingTeamNumberOfWords=" + startingTeamNumberOfWords +
                ", game=" + game +
                '}';
    }
}
