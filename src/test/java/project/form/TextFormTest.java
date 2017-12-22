package project.form;

import org.junit.Test;
import project.configuration.MeanBeanConfiguration;

public class TextFormTest extends MeanBeanConfiguration{

    @Test
    public void gettersAndSettersCorrectness() throws Exception {
        beanTester.testBean(TextForm.class, configuration);
    }
}