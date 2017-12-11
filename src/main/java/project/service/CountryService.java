package project.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.entity.Country;
import project.form.GameCountryForm;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    Page<Country> findAll(Pageable pageable);
    void saveGameCountryForm(GameCountryForm gameCountryForm);
    GameCountryForm findForGameCountryForm(Long id);
    void deleteById(Long id);
}
