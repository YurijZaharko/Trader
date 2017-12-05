package project.service.implementation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import project.entity.Country;
import project.form.GameCountryForm;
import project.form.populator.CountryFormPopulator;
import project.repository.CountryRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceImplTest {
    @Mock
    CountryRepository countryRepository;
    @Mock
    private CountryFormPopulator countryFormPopulator;
    @InjectMocks
    private CountryServiceImpl unit;
    @Mock
    private Pageable pageable;
    @Mock
    private GameCountryForm gameCountryForm;
    @Mock
    private Country country;

    @Test
    public void findAll() throws Exception {
        //when
        unit.findAll();
        //then
        Mockito.verify(countryRepository, Mockito.atLeastOnce()).findAll();
    }

    @Test
    public void findAllPageable() throws Exception {
        //when
        unit.findAll(pageable);
        //then
        Mockito.verify(countryRepository, Mockito.atLeastOnce()).findAll(Mockito.any(Pageable.class));
    }

    @Test
    public void saveGameCountryForm() throws Exception {
        //given
        when(countryFormPopulator.convertFormEntity(gameCountryForm)).thenReturn(country);
        //when
        unit.saveGameCountryForm(gameCountryForm);
        //then
        Mockito.verify(countryRepository, Mockito.atLeastOnce()).save(Mockito.any(Country.class));
    }

    @Test
    public void findForGameCountryForm() throws Exception {
        //given
        when(countryRepository.findOne(anyLong())).thenReturn(country);
        when(countryFormPopulator.convertEntityForm(any(Country.class))).thenReturn(gameCountryForm);
        //when
        unit.findForGameCountryForm(anyLong());
        //then
        verify(countryFormPopulator, atLeastOnce()).convertEntityForm(any(Country.class));
    }

    @Test
    public void deleteById() throws Exception {
        //given
        Long expected = 5L;
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        unit.deleteById(expected);
        //then
        verify(countryRepository, atLeastOnce()).delete(captor.capture());
        Long actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }
}