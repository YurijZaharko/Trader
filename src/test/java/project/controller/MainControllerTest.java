package project.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.form.RegistrationForm;
import project.form.validator.RegistrationValidator;
import project.service.TraderUserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BindingResult bindingResult;
    @Mock
    private TraderUserService traderUserService;
    @Spy
    private RegistrationValidator registrationValidator;
    @InjectMocks
    private MainController unit;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(unit)
                .setViewResolvers(resolver)
                .build();

        registrationValidator.setEmailPattern("^[a-zA-Z0-9._-]{3,15}$");
        registrationValidator.setNickNamePattern("^[a-zA-Z0-9._-]{3,15}$");
    }

    @Test
    public void loginPage() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void getRegistration() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void saveRegistrationData_BindingResult_HasError() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(post("/registration")
                .param("email", "t")
                .param("password", "anyPassword")
                .param("passwordRepeat", "anyPassword")
                .param("nickName", "nickName"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("registrationForm"))
                .andExpect(view().name("registration"));
    }

    @Test
    public void saveRegistrationData() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(post("/registration")
                .param("email", "scipgames")
                .param("password", "anyPassword")
                .param("passwordRepeat", "anyPassword")
                .param("nickName", "nickName"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        Mockito.verify(traderUserService).saveRegistrationForm(Mockito.any(RegistrationForm.class));
    }


    @Test
    public void getHelp() throws Exception {
        //given
        String expected = "testLinkName";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.when(traderUserService.findHelpPage(Mockito.anyString())).thenReturn("");
        //when
        //then
        this.mockMvc.perform(get("/help/{linkName}", expected))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("mainText"))
                .andExpect(MockMvcResultMatchers.view().name("help"));

        Mockito.verify(traderUserService).findHelpPage(captor.capture());
        String actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }
}