package project.form.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import project.config.PathConfiguration;
import project.entity.Country;
import project.entity.GameAdditions;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.repository.CountryRepository;
import project.repository.GameAdditionsRepository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameTypeFormPopulator {
    private GameAdditionsRepository gameAdditionsRepository;
    private CountryRepository countryRepository;
    private PathConfiguration pathConfiguration;

    public GameType convertFormEntity(GameTypeForm gameTypeForm) {
        GameType gameType = new GameType();
        setId(gameTypeForm, gameType);
        setGameName(gameTypeForm, gameType);
        setImageExtension(gameTypeForm, gameType);
        setCountries(gameTypeForm, gameType);
        setGameAdditions(gameTypeForm, gameType);
        return gameType;
    }

    private void setGameName(GameTypeForm gameTypeForm, GameType gameType) {
        String gameName = gameTypeForm.getGameName();
        gameType.setGameName(gameName);
    }

    private void setId(GameTypeForm gameTypeForm, GameType gameType) {
        Long id = gameTypeForm.getId();
        if (id != null) {
            gameType.setId(id);
        }
    }

    private void setImageExtension(GameTypeForm gameTypeForm, GameType gameType) {
        Long id = gameTypeForm.getId();
        MultipartFile multipartFile = gameTypeForm.getMultipartFile();
        String fileExtension = saveMultipartFile(multipartFile, id);
        gameType.setImageExtension(fileExtension);
    }

    private void setCountries(GameTypeForm gameTypeForm, GameType gameType) {
        List<Long> countriesId = gameTypeForm.getCountriesId();
        if (isListEmpty(countriesId)) {
            Set<Country> byIdIn = countryRepository.findByIdIn(countriesId);
            gameType.setCountries(byIdIn);
        }
    }


    private void setGameAdditions(GameTypeForm gameTypeForm, GameType gameType) {
        List<Long> gameAdditionsId = gameTypeForm.getGameAdditionsId();
        if (isListEmpty(gameAdditionsId)) {
            Set<GameAdditions> byIdIn = gameAdditionsRepository.findByIdIn(gameAdditionsId);
            gameType.setGameAdditions(byIdIn);
        }
    }

    private String saveMultipartFile(MultipartFile multipartFile, Long id) {
        if (isEmptyMultipartFile(multipartFile)) {
            String fileExtension = extractFileExtension(multipartFile);
            String newFileNameWithExtension = String.valueOf(id) + fileExtension;
            File imageDirectory = pathConfiguration.getImageDirectory();
            File imageFile = new File(imageDirectory, newFileNameWithExtension);

            try {
                multipartFile.transferTo(imageFile);
                return fileExtension;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private String extractFileExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        int indexOfDot = originalFilename.lastIndexOf('.');
        return originalFilename.substring(indexOfDot);
    }

    private boolean isEmptyMultipartFile(MultipartFile multipartFile) {
        return multipartFile != null && !multipartFile.isEmpty();
    }

    private boolean isListEmpty(List<Long> list) {
        return list != null && !list.isEmpty();
    }

    public GameTypeForm convertEntityForm(GameType one) {
        GameTypeForm gameTypeForm = new GameTypeForm();

        Long id = one.getId();
        String gameName = one.getGameName();
        Set<Country> countries = one.getCountries();
        Set<GameAdditions> gameAdditions = one.getGameAdditions();

        gameTypeForm.setId(id);
        gameTypeForm.setGameName(gameName);
        setCountryId(countries, gameTypeForm);
        setGameAdditionsId(gameAdditions, gameTypeForm);

        return gameTypeForm;
    }

    private void setCountryId(Set<Country> countries, GameTypeForm gameTypeForm){
        if (countries != null) {
            List<Long> countriesId = countries.stream().map(Country::getId).collect(Collectors.toList());
            gameTypeForm.setCountriesId(countriesId);
        } else {
            gameTypeForm.setCountriesId(Collections.emptyList());
        }
    }

    private void setGameAdditionsId(Set<GameAdditions> gameAdditions, GameTypeForm gameTypeForm){
        if (gameAdditions != null) {
            List<Long> gameAdditionsId = gameAdditions.stream().map(GameAdditions::getId).collect(Collectors.toList());
            gameTypeForm.setGameAdditionsId(gameAdditionsId);
        } else {
            gameTypeForm.setGameAdditionsId(Collections.emptyList());
        }
    }

    @Autowired
    public void setGameAdditionsRepository(GameAdditionsRepository gameAdditionsRepository) {
        this.gameAdditionsRepository = gameAdditionsRepository;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Autowired
    public void setPathConfiguration(PathConfiguration pathConfiguration) {
        this.pathConfiguration = pathConfiguration;
    }
}
