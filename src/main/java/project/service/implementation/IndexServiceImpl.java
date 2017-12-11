package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.GameType;
import project.form.IndexForm;
import project.service.GameTypeService;
import project.service.IndexService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    private GameTypeService gameTypeService;


    @Override
    public List<GameType> find(IndexForm indexForm) {
        // TODO: complete method public List<GameType> find(IndexForm indexForm);
        return Collections.emptyList();
    }
    
    @Override
    public List<GameType> findAll() {
        List<GameType> gameTypeListFetchAll = gameTypeService.findAllFetchAll();
        return prepareToFront(gameTypeListFetchAll);
    }

    private List<GameType> prepareToFront(List<GameType> gameTypeListFetchAll) {
        final int quantityOFColumn = 4;
        final int listSize = gameTypeListFetchAll.size();
        List<GameType> resultList = warmUp(listSize);

        int count = 0;
        for (int i = 0; i < quantityOFColumn; i++) {
            for (int j = i; j < listSize; j += quantityOFColumn) {
                GameType gameType = gameTypeListFetchAll.get(count);
                count++;
                resultList.set(j, gameType);
            }
        }
        return resultList;
    }

    private List<GameType> warmUp(int size) {
        List<GameType> resultList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            resultList.add(i, null);
        }
        return resultList;
    }

    @Autowired
    public void setGameTypeService(GameTypeService gameTypeService) {
        this.gameTypeService = gameTypeService;
    }
}
