package project.form.populator;

import org.springframework.beans.factory.annotation.Autowired;
import project.entity.Country;
import project.entity.GameAdditions;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.repository.CountryRepository;
import project.repository.GameAdditionsRepository;

import java.util.List;

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

        List<Long> countries = gameTypeForm.getCountries();
        if (countries != null) {
            List<Country> countryList = countryRepository.findByIdIn(countries);
            gameType.setCountries(countryList);
        }

        List<Long> gameAdditions = gameTypeForm.getGameAdditions();
        if (gameAdditions != null){
            List<GameAdditions> gameAdditionsList = gameAdditionsRepository.findByIdIn(gameAdditions);
            gameType.setGameAdditions(gameAdditionsList);
        }

        return gameType;
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
