package project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.form.IndexForm;

import java.util.List;

public interface GameTypeService {
    Page<GameType> findAll(Pageable pageable);

    void saveGameTypeForm(GameTypeForm gameTypeForm);

    void deleteById(Long id);

    GameTypeForm findForForm(Long id);

    List<GameType> findAllFetchAll();

    List<GameType> findSearch(IndexForm indexForm);

}
