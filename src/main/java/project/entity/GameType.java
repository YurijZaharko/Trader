package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class GameType {
    private Long id;
    private List<Country> countries;
    private List<GameAdditions> gameAdditions;
    private String gameName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @ManyToMany
    @JoinTable(name = "GameType_Country", joinColumns =
    @JoinColumn(name = "fk_Country"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameType"))
    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @ManyToMany
    @JoinTable(name = "GameType_GameAdditions", joinColumns =
    @JoinColumn(name = "fk_Country"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameAdditions"))
    public List<GameAdditions> getGameAdditions() {
        return gameAdditions;
    }

    public void setGameAdditions(List<GameAdditions> gameAdditions) {
        this.gameAdditions = gameAdditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameType)) return false;

        GameType gameType = (GameType) o;

        return gameName.equals(gameType.gameName);
    }

    @Override
    public int hashCode() {
        return gameName.hashCode();
    }
}
