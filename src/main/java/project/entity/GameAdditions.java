package project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GameAdditions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "GameType_GameAdditions", joinColumns =
    @JoinColumn(name = "fk_GameType"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameAdditions"))
    private Set<GameType> gameTypes;
    private String gameAdditionsName;

    public GameAdditions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameAdditionsName() {
        return gameAdditionsName;
    }

    public void setGameAdditionsName(String gameAdditionsName) {
        this.gameAdditionsName = gameAdditionsName;
    }

    public Set<GameType> getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(Set<GameType> gameTypes) {
        this.gameTypes = gameTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameAdditions)) return false;

        GameAdditions that = (GameAdditions) o;

        return gameAdditionsName.equals(that.gameAdditionsName);
    }

    @Override
    public int hashCode() {
        return gameAdditionsName.hashCode();
    }

    @Override
    public String toString() {
        return "GameAdditions{" +
                "id=" + id +
                ", gameTypes=" + gameTypes +
                ", gameAdditionsName='" + gameAdditionsName + '\'' +
                '}';
    }
}
