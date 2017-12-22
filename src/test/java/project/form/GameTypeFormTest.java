package project.form;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.lang.Factory;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import project.configuration.MeanBeanConfiguration;

public class GameTypeFormTest extends MeanBeanConfiguration{
    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        this.configuration = new ConfigurationBuilder()
                .overrideFactory("multipartFile", new MultipartFileFactory())
                .build();
    }

    @Test
    public void gettersAndSettersCorrectness() throws Exception {
        beanTester.testBean(GameTypeForm.class, configuration);
    }

    class MultipartFileFactory implements Factory<MultipartFile>{

        @Override
        public MultipartFile create() {
            return new MockMultipartFile("test", new byte[1]);
        }
    }
}