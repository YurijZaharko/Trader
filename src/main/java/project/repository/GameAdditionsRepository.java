package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.entity.GameAdditions;

import java.util.List;
import java.util.Set;

public interface GameAdditionsRepository extends JpaRepository<GameAdditions, Long>, JpaSpecificationExecutor<GameAdditions> {
    Set<GameAdditions> findByIdIn(List<Long> gameAdditions);

    GameAdditions findByGameAdditionsName(String text);
}
