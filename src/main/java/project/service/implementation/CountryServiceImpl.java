package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Country;
import project.repository.CountryRepository;
import project.service.CountryService;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
}
