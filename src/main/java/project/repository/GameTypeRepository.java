package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.entity.GameType;

import java.util.List;

public interface GameTypeRepository extends JpaRepository<GameType, Long>, JpaSpecificationExecutor<GameType> {

    @Query(value = "SELECT DISTINCT game FROM GameType game LEFT JOIN FETCH game.countries LEFT JOIN FETCH game.gameAdditions WHERE game.id=:id")
    GameType findOneFetchAll(@Param("id") Long id);

    @Query(value = "SELECT DISTINCT game FROM GameType game LEFT JOIN FETCH game.countries LEFT JOIN FETCH game.gameAdditions ORDER BY game.gameName ASC")
    List<GameType> findAllFetchAll();
}
