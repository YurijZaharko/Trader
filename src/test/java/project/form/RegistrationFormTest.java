package project.form;

import org.junit.Test;
import project.configuration.MeanBeanConfiguration;

public class RegistrationFormTest extends MeanBeanConfiguration{

    @Test
    public void gettersAndSettersCorrectness() throws Exception {
        beanTester.testBean(RegistrationForm.class, configuration);
    }
}