package project.form.populator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;
import project.config.PathConfiguration;
import project.entity.Country;
import project.entity.GameAdditions;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.repository.CountryRepository;
import project.repository.GameAdditionsRepository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTypeFormPopulatorTest {
    private Long idExpected = 5L;
    private Long idCountryExpected = 10L;
    private Long idGameAdditionsExpected = 20L;
    private String gameNameExpected = "name";
    private File imageDirectoryExpected = new File("/");
    private Set<GameAdditions> gameAdditionsSet;
    private Set<Country> countrySet;

    @Spy
    private GameType gameType = new GameType();

    @Spy
    private GameTypeForm gameTypeForm = new GameTypeForm();

    @Mock
    private MultipartFile multipartFileExpected;

    @Mock
    private List<Long> countriesId;

    @Mock
    private List<Long> gameAdditionsId;

    @Mock
    private GameAdditionsRepository gameAdditionsRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private PathConfiguration pathConfiguration;

    @InjectMocks
    private GameTypeFormPopulator unit;

    @Before
    public void setUp() throws Exception {
        String originalFilename = "test.jpg";

        gameTypeForm.setId(idExpected);
        gameTypeForm.setGameName(gameNameExpected);

        when(gameTypeForm.getMultipartFile()).thenReturn(multipartFileExpected);
        when(multipartFileExpected.getOriginalFilename()).thenReturn(originalFilename);
        when(pathConfiguration.getImageDirectory()).thenReturn(imageDirectoryExpected);
        when(gameTypeForm.getCountriesId()).thenReturn(countriesId);
        when(countriesId.isEmpty()).thenReturn(false);
        when(countryRepository.findByIdIn(countriesId)).thenReturn(Collections.emptySet());
        when(gameTypeForm.getGameAdditionsId()).thenReturn(gameAdditionsId);
        when(gameAdditionsId.isEmpty()).thenReturn(false);
        when(gameAdditionsRepository.findByIdIn(gameAdditionsId)).thenReturn(Collections.emptySet());

        gameType.setId(idExpected);
        gameType.setGameName(gameNameExpected);

        Country country = new Country();
        country.setId(idCountryExpected);
        country.setFullName("full");
        country.setShortName("short");
        countrySet = new HashSet<>();
        countrySet.add(country);

        GameAdditions gameAdditions = new GameAdditions();
        gameAdditions.setId(idGameAdditionsExpected);
        gameAdditions.setGameAdditionsName("name");
        gameAdditionsSet = new HashSet<>();
        gameAdditionsSet.add(gameAdditions);
    }

    @Test
    public void convertFormEntityNonEmptyMultipartFile() throws Exception {
        //given
        String imageExtensionExpected = ".jpg";
        when(multipartFileExpected.isEmpty()).thenReturn(false);

        //when
        GameType gameType = unit.convertFormEntity(gameTypeForm);
        //then
        Long idActual = gameType.getId();
        String gameNameActual = gameType.getGameName();
        String imageExtensionActual = gameType.getImageExtension();
        Set<Country> countriesActual = gameType.getCountries();
        Set<GameAdditions> gameAdditionsActual = gameType.getGameAdditions();

        assertEquals(idExpected, idActual);
        assertEquals(gameNameExpected, gameNameActual);
        assertEquals(imageExtensionExpected, imageExtensionActual);
        assertNotNull(countriesActual);
        assertNotNull(gameAdditionsActual);
    }

    @Test
    public void convertFormEntityEmptyMultipartFile() throws Exception {
        //given
        when(multipartFileExpected.isEmpty()).thenReturn(true);
        //when
        GameType gameType = unit.convertFormEntity(gameTypeForm);
        //then
        String imageExtensionActual = gameType.getImageExtension();
        assertTrue(imageExtensionActual.isEmpty());

    }

    @Test
    public void convertFormEntityExpectedException() throws Exception {
        //given
        when(multipartFileExpected.isEmpty()).thenReturn(false);
        Mockito.doThrow(new IOException()).when(multipartFileExpected).transferTo(any(File.class));
        //when
        GameType gameType = unit.convertFormEntity(gameTypeForm);
        //then
        String imageExtensionActual = gameType.getImageExtension();
        assertTrue(imageExtensionActual.isEmpty());
    }

    @Test
    public void convertEntityFormNotNullSet() throws Exception {
        //given
        when(gameType.getCountries()).thenReturn(Collections.emptySet());
        when(gameType.getGameAdditions()).thenReturn(Collections.emptySet());
        when(gameType.getCountries()).thenReturn(countrySet);
        when(gameType.getGameAdditions()).thenReturn(gameAdditionsSet);
        //when
        GameTypeForm gameTypeForm = unit.convertEntityForm(gameType);
        //then
        Long idActual = gameTypeForm.getId();
        String gameNameActual = gameTypeForm.getGameName();
        List<Long> countriesIdActual = gameTypeForm.getCountriesId();
        List<Long> gameAdditionsIdActual = gameTypeForm.getGameAdditionsId();

        assertFalse(countriesIdActual.isEmpty());
        assertFalse(gameAdditionsIdActual.isEmpty());
        assertEquals(idExpected, idActual);
        assertEquals(gameNameExpected, gameNameActual);

        Long idCountryActual = gameTypeForm.getCountriesId().get(0);
        Long idGameAdditionsActual = gameTypeForm.getGameAdditionsId().get(0);
        assertEquals(idCountryExpected,idCountryActual);
        assertEquals(idGameAdditionsExpected, idGameAdditionsActual);
    }

    @Test
    public void convertEntityFormNullSet() throws Exception {
        //given
        when(gameType.getCountries()).thenReturn(null);
        when(gameType.getGameAdditions()).thenReturn(null);
        //when
        GameTypeForm gameTypeForm = unit.convertEntityForm(gameType);
        //then
        List<Long> gameAdditionsIdActual = gameTypeForm.getGameAdditionsId();
        List<Long> countriesIdActual = gameTypeForm.getCountriesId();

        assertTrue(gameAdditionsIdActual.isEmpty());
        assertTrue(countriesIdActual.isEmpty());
    }


}