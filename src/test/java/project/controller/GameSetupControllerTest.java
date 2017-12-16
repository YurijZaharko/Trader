package project.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.entity.Country;
import project.entity.GameAdditions;
import project.entity.GameType;
import project.form.GameAdditionsForm;
import project.form.GameCountryForm;
import project.form.GameTypeForm;
import project.service.CountryService;
import project.service.GameAdditionsService;
import project.service.GameTypeService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class GameSetupControllerTest {
    private MockMvc mockMvc;
    @Mock
    private Page<GameType> pageGameType;
    @Mock
    private Page<GameAdditions> pageGameAdditions;
    @Mock
    private Page<Country> pageCountry;
    @Mock
    private Pageable pageable;
    @Mock
    private CountryService countryService;
    @Mock
    private GameAdditionsService gameAdditionsService;
    @Mock
    private GameTypeService gameTypeService;
    @InjectMocks
    private GameSetupController unit;


    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(unit)
                .setViewResolvers(resolver)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void getSetup() throws Exception {
        //given
        Country country = new Country();
        country.setFullName("test_1");
        country.setShortName("tes_1");
        List<Country> countries = Collections.singletonList(country);

        GameAdditions gameAdditions = new GameAdditions();
        gameAdditions.setGameAdditionsName("test_name");
        List<GameAdditions> gameAdditionsList = Collections.singletonList(gameAdditions);

        when(countryService.findAll()).thenReturn(countries);
        when(gameAdditionsService.findAll()).thenReturn(gameAdditionsList);
        when(gameTypeService.findAll(Mockito.any(Pageable.class))).thenReturn(pageGameType);
        //when
        //then
        this.mockMvc.perform(get("/admin/setup"))
                .andExpect(model().attributeExists("countries", "gameAdditionsList", "games"))
                .andExpect(view().name("setup"))
                .andExpect(model().attribute("countries", countries))
                .andExpect(model().attribute("gameAdditionsList", gameAdditionsList));
    }

    @Test
    public void saveGame() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(post("/admin/setup/saveGame")
        .param("gameName", "testParam")
        .param("countriesId", "1", "2", "3")
        .param("gameAdditionsId", "5", "6"))
        .andExpect(model().attributeExists("gameTypeForm"))
        .andExpect(redirectedUrl("/admin/setup"));

        Mockito.verify(gameTypeService, Mockito.atLeastOnce()).saveGameTypeForm(Mockito.any(GameTypeForm.class));
        }

    @Test
    public void editGame() throws Exception {
        //given
        Long anyId = 10L;
        when(gameTypeService.findForForm(Mockito.anyLong())).thenReturn(new GameTypeForm());
        when(gameTypeService.findAll(Mockito.any(Pageable.class))).thenReturn(pageGameType);
        //when
        //then
        this.mockMvc.perform(get("/admin/game/edit/{id}", anyId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gameTypeForm", "gameAdditionsList", "games", "countries"))
                .andExpect(view().name("setup"));
    }

    @Test
    public void deleteGame() throws Exception {
        //given
        Long expected = 10L;
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        //then
        this.mockMvc.perform(get("/admin/game/delete/{id}", expected))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/setup"));
        Mockito.verify(gameTypeService, atLeastOnce()).deleteById(captor.capture());
        Long actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAdditions() throws Exception {
        //given
        when(gameAdditionsService.findAll(any(Pageable.class))).thenReturn(pageGameAdditions);
        //when
        //then
        this.mockMvc.perform(get("/admin/setupAdditions"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("additions"))
                .andExpect(view().name("setupAdditions"));
    }

    @Test
    public void saveAdditions() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(post("/admin/gameAdditions/save"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/setupAdditions"));

        verify(gameAdditionsService, atLeastOnce()).save(any(GameAdditionsForm.class));
    }

    @Test
    public void editAdditions() throws Exception {
        //given
        Long id = 88L;
        when(gameAdditionsService.findForGameAdditionsForm(anyLong())).thenReturn(new GameAdditionsForm());
        when(gameAdditionsService.findAll(any(Pageable.class))).thenReturn(pageGameAdditions);
        //when
        //then
        this.mockMvc.perform(get("/admin/addition/edit/{id}", id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gameAdditionsForm", "additions"))
                .andExpect(view().name("setupAdditions"));

    }

    @Test
    public void deleteAdditions() throws Exception {
        //given
        Long expected = 6548L;
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        //then
        this.mockMvc.perform(get("/admin/addition/delete/{id}", expected))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/setupAdditions"));

        verify(gameAdditionsService, atLeastOnce()).deleteById(captor.capture());
        Long actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCountry() throws Exception {
        //given
        when(countryService.findAll(any(Pageable.class))).thenReturn(pageCountry);
        //when
        //then
        this.mockMvc.perform(get("/admin/setupCountries"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"))
                .andExpect(view().name("setupCountries"));
    }

    @Test
    public void saveCountry() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(post("/admin/country/save"))
                .andExpect(status().isFound())
                .andExpect(model().attributeExists("gameCountryForm"))
                .andExpect(redirectedUrl("/admin/setupCountries"));

        verify(countryService, atLeastOnce()).saveGameCountryForm(any(GameCountryForm.class));
    }

    @Test
    public void editCountry() throws Exception {
        //given
        Long expected = 68L;
        when(countryService.findForGameCountryForm(anyLong())).thenReturn(new GameCountryForm());
        when(countryService.findAll(any(Pageable.class))).thenReturn(pageCountry);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        //then
        this.mockMvc.perform(get("/admin/country/edit/{id}", expected))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("gameCountryForm", "countries"))
                .andExpect(view().name("setupCountries"));

        verify(countryService).findForGameCountryForm(captor.capture());
        Long actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteCountry() throws Exception {
        //given
        Long expected = 53L;
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        //then
        this.mockMvc.perform(get("/admin/country/delete/{id}", expected))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/setupCountries"));

        verify(countryService).deleteById(captor.capture());
        Long actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }
}