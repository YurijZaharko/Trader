package project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.entity.GameAdditions;
import project.form.GameAdditionsForm;

import java.util.List;

public interface GameAdditionsService {
    List<GameAdditions> findAll();

    GameAdditionsForm findForForm(Long id);

    Page<GameAdditions> findAll(Pageable pageable);
}
