package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.entity.Country;
import project.form.GameCountryForm;
import project.form.populator.CountryFormPopulator;
import project.repository.CountryRepository;
import project.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryRepository countryRepository;
    private CountryFormPopulator countryFormPopulator;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    public void saveGameCountryForm(GameCountryForm gameCountryForm) {
        Country country = countryFormPopulator.convertFormEntity(gameCountryForm);
        countryRepository.save(country);
    }

    @Override
    public GameCountryForm findForGameCountryForm(Long id) {
        Country one = countryRepository.findOne(id);
        return countryFormPopulator.convertEntityForm(one);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.delete(id);
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Autowired
    public void setCountryFormPopulator(CountryFormPopulator countryFormPopulator) {
        this.countryFormPopulator = countryFormPopulator;
    }
}
