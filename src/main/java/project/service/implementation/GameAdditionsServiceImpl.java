package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.GameAdditions;
import project.repository.GameAdditionsRepository;
import project.service.GameAdditionsService;

import java.util.List;

@Service
public class GameAdditionsServiceImpl implements GameAdditionsService {
    private GameAdditionsRepository gameAdditionsRepository;

    @Override
    public List<GameAdditions> findAll() {
        return gameAdditionsRepository.findAll();
    }

    @Autowired
    public void setGameAdditionsRepository(GameAdditionsRepository gameAdditionsRepository) {
        this.gameAdditionsRepository = gameAdditionsRepository;
    }
}
