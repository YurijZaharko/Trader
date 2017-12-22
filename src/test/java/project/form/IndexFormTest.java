package project.form;

import org.junit.Test;
import project.configuration.MeanBeanConfiguration;

public class IndexFormTest extends MeanBeanConfiguration{

    @Test
    public void gettersAndSettersCorrectness() throws Exception {
        beanTester.testBean(IndexForm.class, configuration);
    }
}