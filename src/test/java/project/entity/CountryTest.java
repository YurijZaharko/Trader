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
    public void equalsAndHashCorrectness(){
        String sameShortName = "UA";
        String sameFullName = "Ukraine";

        Country countryBlack = new Country();
        countryBlack.setShortName(sameShortName);
        countryBlack.setFullName(sameFullName);

        Country countryRed = new Country();
        countryRed.setShortName(sameShortName);
        countryRed.setFullName(sameFullName);

        Assert.assertTrue(countryBlack.equals(countryRed));
        Assert.assertTrue(countryBlack.hashCode() == countryRed.hashCode());
    }

}