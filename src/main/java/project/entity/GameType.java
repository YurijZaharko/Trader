package project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GameType {

    private Long id;
    private Set<Country> countries;
    private Set<GameAdditions> gameAdditions;
    private String gameName;
    private String imageExtension;

    public GameType() {
    }

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
    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @ManyToMany
    @JoinTable(name = "GameType_GameAdditions", joinColumns =
    @JoinColumn(name = "fk_GameAdditions"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameType"))
    public Set<GameAdditions> getGameAdditions() {
        return gameAdditions;
    }

    public void setGameAdditions(Set<GameAdditions> gameAdditions) {
        this.gameAdditions = gameAdditions;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
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
