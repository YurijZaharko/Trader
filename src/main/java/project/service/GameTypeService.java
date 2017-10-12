package project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.entity.GameType;
import project.form.GameTypeForm;

public interface GameTypeService {
    Page<GameType> findAll(Pageable pageable);

    void saveGameTypeForm(GameTypeForm gameTypeForm);
}
