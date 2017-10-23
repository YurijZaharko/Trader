package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.entity.GameAdditions;
import project.form.GameAdditionsForm;
import project.form.populator.GameAdditionsFormPopulator;
import project.repository.GameAdditionsRepository;
import project.service.GameAdditionsService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GameAdditionsServiceImpl implements GameAdditionsService {
    private GameAdditionsRepository gameAdditionsRepository;
    private GameAdditionsFormPopulator gameAdditionsFormPopulator;

    @Override
    public List<GameAdditions> findAll() {
        return gameAdditionsRepository.findAll();
    }

    @Override
    public Page<GameAdditions> findAll(Pageable pageable) {
        return gameAdditionsRepository.findAll(pageable);
    }

    @Override
    public void save(GameAdditionsForm gameAdditionsForm) {
        GameAdditions gameAdditions = gameAdditionsFormPopulator.convertFormEntity(gameAdditionsForm);
        gameAdditionsRepository.save(gameAdditions);
    }

    @Override
    public GameAdditions findOne(Long id) {
        return gameAdditionsRepository.findOne(id);
    }

    @Override
    public GameAdditions findByGameAdditionsName(String text) {
        return gameAdditionsRepository.findByGameAdditionsName(text);
    }

    @Override
    public GameAdditionsForm findForGameAdditionsForm(Long id) {
        GameAdditions one = gameAdditionsRepository.findOne(id);
        return gameAdditionsFormPopulator.convertEntityForm(one);
    }

    @Override
    public void deleteById(Long id) {
        gameAdditionsRepository.delete(id);
    }

    @Autowired
    public void setGameAdditionsRepository(GameAdditionsRepository gameAdditionsRepository) {
        this.gameAdditionsRepository = gameAdditionsRepository;
    }

    @Resource(name = "gameAdditionsFormPopulator")
    public void setGameAdditionsFormPopulator(GameAdditionsFormPopulator gameAdditionsFormPopulator) {
        this.gameAdditionsFormPopulator = gameAdditionsFormPopulator;
    }
}
