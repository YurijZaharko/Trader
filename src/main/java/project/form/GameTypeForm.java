package project.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class GameTypeForm {
    private Long id;
    private String gameName;
    private Set<Integer> countries;
    private Set<Integer> gameAdditions;
    private MultipartFile multipartFile;

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

    public Set<Integer> getCountries() {
        return countries;
    }

    public void setCountries(Set<Integer> countries) {
        this.countries = countries;
    }

    public Set<Integer> getGameAdditions() {
        return gameAdditions;
    }

    public void setGameAdditions(Set<Integer> gameAdditions) {
        this.gameAdditions = gameAdditions;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}