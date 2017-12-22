package project.configuration;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

public abstract class MeanBeanConfiguration {
    protected Configuration configuration = new ConfigurationBuilder()
            .iterations(10)
            .build();

    protected BeanTester beanTester = new BeanTester();
}
