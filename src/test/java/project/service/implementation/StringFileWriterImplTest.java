package project.service.implementation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StringFileWriterImplTest {
    private String filename;
    private String textExpected;
    private File fileExpected;

    StringFileWriterImpl unit = new StringFileWriterImpl();

    @Before
    public void setUp() throws Exception {
        File templateDirectory = new File("src/test/resources/templateDirectory");
        unit.setTemplateDirectory(templateDirectory);
        filename = "test";
        textExpected = "can you write it?";
        fileExpected = new File("src/test/resources/templateDirectory/test.txt");
    }

    @Test
    public void writeFile() throws Exception {
        //given
        String fileNameExpected = filename + ".txt";
        //when
        unit.writeFile(filename, textExpected);
        //then
        assertTrue(fileExpected.exists());
        assertTrue(fileExpected.isFile());
        String fileNameActual = fileExpected.getName();
        assertTrue(fileNameExpected.equals(fileNameActual));
        String textActual = new String(Files.readAllBytes(fileExpected.toPath()), Charset.forName("UTF-8"));
        assertNotNull(textActual);
        assertTrue(textExpected.equals(textActual));
    }

    @After
    public void tearDown() throws Exception {
        if (fileExpected.exists()){
            fileExpected.delete();
        }
    }
}