package project.form.populator;

import org.springframework.beans.factory.annotation.Autowired;
import project.entity.Country;
import project.entity.GameAdditions;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.repository.CountryRepository;
import project.repository.GameAdditionsRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameTypeFormPopulator {
    private GameAdditionsRepository gameAdditionsRepository;
    private CountryRepository countryRepository;

    public GameType convertFormEntity(GameTypeForm gameTypeForm) {
        GameType gameType = new GameType();

        Long id = gameTypeForm.getId();
        if (id != null) {
            gameType.setId(id);
        }

        String gameName = gameTypeForm.getGameName();
        gameType.setGameName(gameName);

//        List<Long> countries = gameTypeForm.getCountriesId();
//        if (countries != null) {
//            Set<Country> countryList = countryRepository.findByIdIn(countries);
//            gameType.setCountries(countryList);
//        }
//
//        List<Long> gameAdditions = gameTypeForm.getGameAdditionsId();
//        if (gameAdditions != null) {
//            Set<GameAdditions> gameAdditionsList = gameAdditionsRepository.findByIdIn(gameAdditions);
//            gameType.setGameAdditions(gameAdditionsList);
//        }

        return gameType;
    }

    public GameTypeForm convertEntityForm(GameType one) {
        GameTypeForm gameTypeForm = new GameTypeForm();
        Long id = one.getId();
        String gameName = one.getGameName();
        Set<Country> countries = one.getCountries();
        Set<GameAdditions> gameAdditions = one.getGameAdditions();

        gameTypeForm.setId(id);
        gameTypeForm.setGameName(gameName);
        if (countries != null){
            List<Long> countriesId = countries.stream().map(Country::getId).collect(Collectors.toList());
            gameTypeForm.setCountriesId(countriesId);
        }

        if (gameAdditions != null){
            List<Long> gameAdditionsId = gameAdditions.stream().map(GameAdditions::getId).collect(Collectors.toList());
            gameTypeForm.setGameAdditionsId(gameAdditionsId);
        }
        return gameTypeForm;
    }

    @Autowired
    public void setGameAdditionsRepository(GameAdditionsRepository gameAdditionsRepository) {
        this.gameAdditionsRepository = gameAdditionsRepository;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
}
