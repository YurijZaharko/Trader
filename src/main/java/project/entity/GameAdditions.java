package project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GameAdditions {
    private Long id;
    private String serviceName;
    private Set<GameType> gameTypes;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @ManyToMany
    @JoinTable(name = "GameType_GameAdditions", joinColumns =
    @JoinColumn(name = "fk_GameAdditions"), inverseJoinColumns =
    @JoinColumn(name = "fk_GameType"))
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

        return serviceName.equals(that.serviceName);
    }

    @Override
    public int hashCode() {
        return serviceName.hashCode();
    }
}
