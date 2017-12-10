package project.service.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.StringUtils;
import project.form.TextForm;
import project.service.StringFileReader;
import project.service.StringFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class FileUtilitiesServiceImplTest {
    private String templateNameExpected = "name";
    private String mainTextExpected = "text";
    private TextForm textForm;
    private File[] files = new File[2];
    @Mock
    private StringFileWriter stringFileWriter;
    @Mock
    private StringFileReader stringFileReader;
    @Mock
    private File templateDirectory;
    @InjectMocks
    private FileUtilitiesServiceImpl unit;
    @Mock
    private File fileExpected;
    @Mock
    private File fileDirectoryNotExpected;

    @Before
    public void setUp() throws Exception {
        textForm = new TextForm();
        textForm.setMainText(mainTextExpected);
        textForm.setTemplateName(templateNameExpected);

        files[0] = fileExpected;
        files[1] = fileDirectoryNotExpected;
    }

    @Test
    public void saveTextFormToFile() throws Exception {
        //given
        ArgumentCaptor<String> firstArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> secondArgument = ArgumentCaptor.forClass(String.class);
        //when
        unit.saveTextFormToFile(textForm);
        //then
        Mockito.verify(stringFileWriter).writeFile(firstArgument.capture(), secondArgument.capture());
        String templateNameActual = firstArgument.getValue();
        String mainTextActual = secondArgument.getValue();
        Assert.assertTrue(templateNameExpected.equals(templateNameActual));
        Assert.assertTrue(mainTextExpected.equals(mainTextActual));
    }

    @Test
    public void getListOfTemplate() throws Exception {
        //given
        String nameExpected = "testName";
        int sizeExpected = 1;
        when(templateDirectory.listFiles()).thenReturn(files);
        when(fileExpected.isFile()).thenReturn(true);
        when(fileExpected.getName()).thenReturn(nameExpected);
        when(fileDirectoryNotExpected.isFile()).thenReturn(false);
        //when
        List<String> listOfTemplate = unit.getListOfTemplate();
        //then
        Assert.assertNotNull(listOfTemplate);
        int sizeActual = listOfTemplate.size();
        Assert.assertEquals(sizeExpected, sizeActual);
        String nameActual = listOfTemplate.get(0);
        Assert.assertTrue(nameExpected.equals(nameActual));
    }

    @Test
    public void showFile() throws Exception {
        //given
        when(stringFileReader.readFromFile(Mockito.any(File.class), Mockito.anyString())).thenReturn(mainTextExpected);
        //when
        TextForm textForm = unit.showFile(templateNameExpected);
        //then
        Assert.assertNotNull(textForm);
        String templateNameActual = textForm.getTemplateName();
        String mainTextActual = textForm.getMainText();
        Assert.assertTrue(templateNameExpected.equals(templateNameActual));
        Assert.assertTrue(mainTextExpected.equals(mainTextActual));
    }

    @Test
    public void showFileWithException() throws Exception {
        //given
        when(stringFileReader.readFromFile(Mockito.any(File.class), Mockito.anyString())).thenThrow(new IOException());
        //when
        TextForm textForm = unit.showFile(templateNameExpected);
        //then
        Assert.assertNotNull(textForm);
        String templateNameActual = textForm.getTemplateName();
        String mainTextActual = textForm.getMainText();
        Assert.assertTrue(templateNameExpected.equals(templateNameActual));
        Assert.assertTrue(StringUtils.isEmpty(mainTextActual));
    }
}