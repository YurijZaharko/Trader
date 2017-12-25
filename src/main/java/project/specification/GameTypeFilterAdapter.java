package project.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import project.entity.GameType;
import project.form.IndexForm;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class GameTypeFilterAdapter implements Specification<GameType> {
    private final String search;
    private final List<Specification<GameType>> filter = new ArrayList<>();
    private static final String ENTITY_FIELD = "gameName";

    public GameTypeFilterAdapter(IndexForm indexForm) {
        this.search = indexForm.getSearch() + "";
    }


    @Override
    public Predicate toPredicate(Root<GameType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        root.fetch("countries", JoinType.LEFT);
        root.fetch("gameAdditions", JoinType.LEFT);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(ENTITY_FIELD)));
        criteriaQuery.distinct(true);

        if (this.search.isEmpty()){
            findAll();
        } else {
            findByGameNameExactly();
            findByGameNameStartWith();
            findByGameNameEndWhith();
        }

        Specifications<GameType> specification = Specifications.where(filter.get(0));
        for (Specification<GameType> gameTypeSpecification : filter.subList(1, filter.size())) {
            specification = specification.or(gameTypeSpecification);
        }

        return specification.toPredicate(root, criteriaQuery, criteriaBuilder);
    }

    private void findByGameNameExactly() {
        filter.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ENTITY_FIELD)), search.toUpperCase()));
    }

    private void findByGameNameStartWith() {
        filter.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ENTITY_FIELD)), this.search.toUpperCase() + "%"));
    }


    private void findByGameNameEndWhith() {
        filter.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ENTITY_FIELD)), "%" + this.search.toUpperCase()));
    }

    private void findAll(){
        filter.add((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(ENTITY_FIELD), "%"));
    }


}
