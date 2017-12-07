package project.form.populator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import project.entity.Country;
import project.form.GameCountryForm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CountryFormPopulatorTest {
    private long idExpected = 5;
    private String fullNameExpected = "full";
    private String shortNameExpected = "short";

    @Mock
    private Country country;
    @Mock
    private GameCountryForm gameCountryForm;
    @Spy
    private CountryFormPopulator unit;

    @Test
    public void convertFormEntityNullId() throws Exception {
        //given
        Mockito.when(gameCountryForm.getCountryId()).thenReturn(null);
        Mockito.when(gameCountryForm.getFullName()).thenReturn(fullNameExpected);
        Mockito.when(gameCountryForm.getShortName()).thenReturn(shortNameExpected);
        //when
        Country country = unit.convertFormEntity(gameCountryForm);
        //then
        Long idActual = country.getId();
        String fullNameActual = country.getFullName();
        String shortNameActual = country.getShortName();
        assertNull(idActual);
        assertEquals(fullNameExpected, fullNameActual);
        assertEquals(shortNameExpected, shortNameActual);
    }

    @Test
    public void convertFormEntityNotNullId() throws Exception {
        Mockito.when(gameCountryForm.getCountryId()).thenReturn(idExpected);
        //when
        Country country = unit.convertFormEntity(gameCountryForm);
        //then
        long idActual = country.getId();
        assertEquals(idExpected, idActual);

    }

    @Test
    public void convertEntityForm() throws Exception {
        //given
        Mockito.when(country.getId()).thenReturn(idExpected);
        Mockito.when(country.getFullName()).thenReturn(fullNameExpected);
        Mockito.when(country.getShortName()).thenReturn(shortNameExpected);
        //when
        GameCountryForm gameCountryForm = unit.convertEntityForm(country);
        //then
        long countryIdActual = gameCountryForm.getCountryId();
        String fullNameActual = gameCountryForm.getFullName();
        String shortNameActual = gameCountryForm.getShortName();
        Assert.assertEquals(idExpected, countryIdActual);
        Assert.assertEquals(fullNameExpected, fullNameActual);
        Assert.assertEquals(shortNameExpected, shortNameActual);
    }

}