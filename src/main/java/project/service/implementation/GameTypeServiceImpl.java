package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.form.IndexForm;
import project.form.populator.GameTypeFormPopulator;
import project.repository.GameTypeRepository;
import project.service.GameTypeService;
import project.specification.GameTypeFilterAdapter;

import java.util.List;

@Service
public class GameTypeServiceImpl implements GameTypeService {
    private GameTypeRepository gameTypeRepository;
    private GameTypeFormPopulator gameTypeFormPopulator;

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

    @Override
    public List<GameType> findSearch(IndexForm indexForm) {
        List<GameType> all = gameTypeRepository.findAll(new GameTypeFilterAdapter(indexForm));
        return all;
    }

    @Autowired
    public void setGameTypeFormPopulator(GameTypeFormPopulator gameTypeFormPopulator) {
        this.gameTypeFormPopulator = gameTypeFormPopulator;
    }

    @Autowired
    public void setGameTypeRepository(GameTypeRepository gameTypeRepository) {
        this.gameTypeRepository = gameTypeRepository;
    }
}
