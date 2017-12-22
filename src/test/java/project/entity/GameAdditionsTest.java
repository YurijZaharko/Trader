package project.entity;

import org.junit.Assert;
import org.junit.Test;


public class GameAdditionsTest extends MeanBeanConfiguration{

    @Test
    public void gettersAndSettersCorrectness() {
        beanTester.testBean(GameAdditions.class, configuration);
    }

    @Test
    public void equalsAndHashCorrectness() {
        String sameName = "testName";

        GameAdditions gameAdditionBlack = new GameAdditions();
        gameAdditionBlack.setGameAdditionsName(sameName);

        GameAdditions gameAdditionsRed = new GameAdditions();
        gameAdditionsRed.setGameAdditionsName(sameName);

        Assert.assertTrue(gameAdditionBlack.equals(gameAdditionsRed));
        Assert.assertTrue(gameAdditionBlack.hashCode() == gameAdditionsRed.hashCode());
    }
}