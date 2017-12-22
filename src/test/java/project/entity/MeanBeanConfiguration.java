package project.entity;

import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

abstract class MeanBeanConfiguration {
    Configuration configuration = new ConfigurationBuilder()
            .iterations(10)
            .build();

    BeanTester beanTester = new BeanTester();
}
