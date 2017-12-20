package project.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;


public class CountryTest {
    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        this.configuration = new ConfigurationBuilder()
                .iterations(10)
                .build();
        }

    @Test
    public void getterAndSetterCorrectness() throws Exception{
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Country.class, configuration);
    }

    @Test
    public void equalsContract(){
        Country countryFirst = new Country();
        countryFirst.setShortName("UA");
        countryFirst.setFullName("Ukraine");

        Country countrySecond = new Country();
        countrySecond.setShortName("UA");
        countrySecond.setFullName("Ukraine");

        Assert.assertTrue(countryFirst.equals(countrySecond));
        Assert.assertTrue(countryFirst.hashCode() == countrySecond.hashCode());
    }

}