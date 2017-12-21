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
public class GameTypeTest {
    private Configuration configuration;
    private BeanTester beanTester;

    @Before
    public void setUp() throws Exception {
        this.configuration = new ConfigurationBuilder()
                .iterations(10)
                .build();

        this.beanTester = new BeanTester();
    }

    @Test
    public void gettersAndSettersCorrectness(){
        beanTester.testBean(GameType.class, configuration);
    }

    @Test
    public void equalsAndHashCorrectness(){
        String sameName = "testName";

        GameType gameTypeBlack = new GameType();
        gameTypeBlack.setGameName(sameName);

        GameType gameTypeRed = new GameType();
        gameTypeRed.setGameName(sameName);

        Assert.assertTrue(gameTypeBlack.equals(gameTypeRed));
        Assert.assertTrue(gameTypeBlack.hashCode() == gameTypeRed.hashCode());
    }
}