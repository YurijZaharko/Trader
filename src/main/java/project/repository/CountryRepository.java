package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
