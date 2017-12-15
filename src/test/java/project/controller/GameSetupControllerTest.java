package project.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.service.CountryService;
import project.service.GameAdditionsService;
import project.service.GameTypeService;

@RunWith(MockitoJUnitRunner.class)
public class GameSetupControllerTest {
    @Mock
    private CountryService countryService;
    @Mock
    private GameAdditionsService gameAdditionsService;
    @Mock
    private GameTypeService gameTypeService;
    @InjectMocks
    private GameSetupController unit;

    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).setViewResolvers(resolver).build();
    }

    @Test
    public void getSetup() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void saveGame() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void editGame() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void deleteGame() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void getAdditions() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void saveAdditions() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void editAdditions() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void deleteAdditions() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void getCountry() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void saveCountry() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void editCountry() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void deleteCountry() throws Exception {
        //given
        //when
        //then
    }
}