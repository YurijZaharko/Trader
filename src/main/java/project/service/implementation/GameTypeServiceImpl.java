package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.entity.GameType;
import project.repository.GameTypeRepository;
import project.service.GameTypeService;

@Service
public class GameTypeServiceImpl implements GameTypeService{
    private GameTypeRepository gameTypeRepository;

    @Override
    public Page<GameType> findAll(Pageable pageable) {
        return gameTypeRepository.findAll(pageable);
    }

    @Autowired
    public void setGameTypeRepository(GameTypeRepository gameTypeRepository) {
        this.gameTypeRepository = gameTypeRepository;
    }
}
