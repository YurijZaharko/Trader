package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.GameAdditions;

public interface GameAdditionsRepository extends JpaRepository<GameAdditions, Long> {
}
