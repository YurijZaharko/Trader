package project.entity;

import org.junit.Assert;
import org.junit.Test;


public class CountryTest extends MeanBeanConfiguration{

    @Test
    public void getterAndSetterCorrectness() throws Exception {
        beanTester.testBean(Country.class, configuration);
    }

    @Test
    public void equalsAndHashCorrectness() {
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