package project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.entity.GameAdditions;
import project.form.GameAdditionsForm;

import java.util.List;

public interface GameAdditionsService {
    List<GameAdditions> findAll();

    Page<GameAdditions> findAll(Pageable pageable);

    void save(GameAdditionsForm gameAdditionsForm);

    GameAdditions findOne(Long id);

    GameAdditions findByGameAdditionsName(String text);

    GameAdditionsForm findForGameAdditionsForm(Long id);

    void deleteById(Long id);
}
