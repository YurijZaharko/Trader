package project.service.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class StringFileReaderImplTest {
    private File path;
    private String fileName;

    private StringFileReaderImpl unit = new StringFileReaderImpl();

    @Before
    public void setUp() throws Exception {
        path = new File("src/test/resources/read");
        fileName = "testRead.txt";
    }

    @Test
    public void readFromFile() throws Exception {
        //given
        String expected = "can you read this?";
        //when
        String actual = unit.readFromFile(path, fileName);
        //then
        Assert.assertNotNull(actual);
        Assert.assertTrue(expected.equals(actual));
    }
}