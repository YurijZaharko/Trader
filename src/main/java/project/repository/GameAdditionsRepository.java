package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.GameAdditions;

import java.util.List;

public interface GameAdditionsRepository extends JpaRepository<GameAdditions, Long> {
    List<GameAdditions> findByIdIn(List<Long> gameAdditions);
}
