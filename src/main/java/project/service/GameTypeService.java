package project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.entity.GameType;

public interface GameTypeService {
    Page<GameType> findAll(Pageable pageable);
}
