package project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GameType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "GameType_Country", joinColumns =
    @JoinColumn(name = "fk_Country"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameType"))
    private Set<Country> countries;

    @ManyToMany
    @JoinTable(name = "GameType_GameAdditions", joinColumns =
    @JoinColumn(name = "fk_GameAdditions"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameType"))
    private Set<GameAdditions> gameAdditions;
    private String gameName;
    private String imageExtension;

    public GameType() {
    }

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

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

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

    @Override
    public String toString() {
        return "GameType{" +
                "id=" + id +
                ", countries=" + countries +
                ", gameAdditions=" + gameAdditions +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
