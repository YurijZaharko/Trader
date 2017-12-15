package project.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import project.form.TextForm;
import project.service.FileUtilitiesService;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AdminControllerTest {
    @Mock
    private FileUtilitiesService fileUtilitiesService;
    @InjectMocks
    private AdminController unit;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).setViewResolvers(viewResolver).build();
    }

    @Test
    public void admin() throws Exception {
        //given
        //when
        //then
        this.mockMvc.perform(get("/admin"))
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("textForm"));
    }

    @Test
    public void getFaq() throws Exception {
        //given
        List<String> listTemplatesExpected = new ArrayList<>();
        when(fileUtilitiesService.getListOfTemplate()).thenReturn(listTemplatesExpected);
        //when
        //then
        this.mockMvc.perform(get("/admin/help/faq"))
                .andExpect(view().name("faq"))
                .andExpect(model().attributeExists("listFiles"));

    }

    @Test
    public void showFile() throws Exception {
        //given
        when(fileUtilitiesService.showFile(Mockito.anyString())).thenReturn(new TextForm());
        when(fileUtilitiesService.getListOfTemplate()).thenReturn(Arrays.asList("file_1", "file_2", "file_3"));
        //when
        //then
        this.mockMvc.perform(get("/admin/showFile/{fileName}", "testFileName"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("textForm"))
                .andExpect(model().attributeExists("listFiles"))
                .andExpect(view().name("faq"));
    }

    @Test
    public void saveTemplate() throws Exception {
        //given
        Mockito.doNothing().when(fileUtilitiesService).saveTextFormToFile(Mockito.any(TextForm.class));
        //when
        //then
        this.mockMvc.perform(post("/admin/faq/save")
                .param("templateName", "testTemplateName")
                .param("mainText", "testMainText"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/help/faq"));
    }

    @Test
    public void saveTemplate_shouldThrowException() throws Exception {
        //given
        Mockito.doThrow(new IOException()).when(fileUtilitiesService).saveTextFormToFile(Mockito.any(TextForm.class));
        //when
        //then
        this.mockMvc.perform(post("/admin/faq/save")
                .param("templateName", "testTemplateName")
                .param("mainText", "testMainText"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/admin/help/faq"));


    }
}