package project.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.form.IndexForm;
import project.service.IndexService;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    private MockMvc mockMvc;
    @Mock
    private IndexService indexService;
    @InjectMocks
    private IndexController unit;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        this.mockMvc = MockMvcBuilders.standaloneSetup(unit)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void main() throws Exception {
        //given
        Mockito.when(indexService.findAll()).thenReturn(Collections.emptyList());
        //when
        //then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("listGames"))
                .andExpect(MockMvcResultMatchers.view().name("main"));
    }

    @Test
    public void getSearchResult() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(MockMvcRequestBuilders.get("/find" )
                .param("find", "find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("findIndexForm"))
                .andExpect(MockMvcResultMatchers.view().name("main"));

        Mockito.verify(indexService).find(Mockito.any(IndexForm.class));
    }
}