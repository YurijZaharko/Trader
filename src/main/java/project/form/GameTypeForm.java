package project.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class GameTypeForm {
    private Long id;
    private String gameName;
    private List<Long> countries;
    private List<Long> gameAdditions;
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

    public List<Long> getCountries() {
        return countries;
    }

    public void setCountries(List<Long> countries) {
        this.countries = countries;
    }

    public List<Long> getGameAdditions() {
        return gameAdditions;
    }

    public void setGameAdditions(List<Long> gameAdditions) {
        this.gameAdditions = gameAdditions;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
