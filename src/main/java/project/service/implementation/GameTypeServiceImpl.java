package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.form.populator.GameTypeFormPopulator;
import project.repository.CountryRepository;
import project.repository.GameAdditionsRepository;
import project.repository.GameTypeRepository;
import project.service.GameTypeService;

import java.util.List;

@Service
public class GameTypeServiceImpl implements GameTypeService {
    private GameTypeRepository gameTypeRepository;
    private GameTypeFormPopulator gameTypeFormPopulator;
    private GameAdditionsRepository gameAdditionsRepository;
    private CountryRepository countryRepository;

    @Override
    public Page<GameType> findAll(Pageable pageable) {
        return gameTypeRepository.findAll(pageable);
    }

    @Override
    public void saveGameTypeForm(GameTypeForm gameTypeForm) {
        GameType gameType = gameTypeFormPopulator.convertFormEntity(gameTypeForm);
        gameTypeRepository.saveAndFlush(gameType);
    }

    @Override
    public void deleteById(Long id) {
        gameTypeRepository.delete(id);
    }

    @Override
    public GameTypeForm findForForm(Long id) {
        GameType one = gameTypeRepository.findOneFetchAll(id);
        return gameTypeFormPopulator.convertEntityForm(one);
    }

    @Override
    public List<GameType> findAllFetchAll() {
        return gameTypeRepository.findAllFetchAll();
    }

    @Autowired
    public void setGameTypeFormPopulator(GameTypeFormPopulator gameTypeFormPopulator) {
        this.gameTypeFormPopulator = gameTypeFormPopulator;
    }

    @Autowired
    public void setGameTypeRepository(GameTypeRepository gameTypeRepository) {
        this.gameTypeRepository = gameTypeRepository;
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
