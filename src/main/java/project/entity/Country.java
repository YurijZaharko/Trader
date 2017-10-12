package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {
    private Long id;
    private List<GameType> gameTypes;
    private String shortName;
    private String fullName;

    public Country() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @ManyToMany
    @JoinTable(name = "GameType_Country", joinColumns =
    @JoinColumn(name = "fk_GameType"), inverseJoinColumns =
    @JoinColumn(name = "fk_Country"))
    public List<GameType> getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(List<GameType> gameTypes) {
        this.gameTypes = gameTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (!shortName.equals(country.shortName)) return false;
        return fullName.equals(country.fullName);
    }

    @Override
    public int hashCode() {
        int result = shortName.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }
}
