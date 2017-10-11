package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class GameAdditions {
    private Long id;
    private String gameAdditionsName;
    private List<GameType> gameTypes;

    @Id
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

    @ManyToMany
    @JoinTable(name = "GameType_GameAdditions", joinColumns =
    @JoinColumn(name = "fk_GameAdditions"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameType"))
    public List<GameType> getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(List<GameType> gameTypes) {
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
}
