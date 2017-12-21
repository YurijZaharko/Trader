package project.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameAdditionsTest {
    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        this.configuration = new ConfigurationBuilder()
                .iterations(10)
                .build();
        }

    @Test
    public void gettersAndSettersCorrectness(){
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(GameAdditions.class, configuration);
    }

    @Test
    public void equalsAndHashCorrectness(){
        String sameName = "testName";

        GameAdditions gameAdditionBlack = new GameAdditions();
        gameAdditionBlack.setGameAdditionsName(sameName);

        GameAdditions gameAdditionsRed = new GameAdditions();
        gameAdditionsRed.setGameAdditionsName(sameName);

        Assert.assertTrue(gameAdditionBlack.equals(gameAdditionsRed));
        Assert.assertTrue(gameAdditionBlack.hashCode() == gameAdditionsRed.hashCode());
    }
}