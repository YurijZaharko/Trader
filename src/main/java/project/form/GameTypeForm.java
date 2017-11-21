package project.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@Scope(value = "prototype")
public class GameTypeForm {
    private Long id;
    private String gameName;
    private List<Long> countriesId;
    private List<Long> gameAdditionsId;
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

    public List<Long> getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(List<Long> countriesId) {
        this.countriesId = countriesId;
    }

    public List<Long> getGameAdditionsId() {
        return gameAdditionsId;
    }

    public void setGameAdditionsId(List<Long> gameAdditionsId) {
        this.gameAdditionsId = gameAdditionsId;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
