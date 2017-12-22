package project.entity;

import org.junit.Assert;
import org.junit.Test;

public class GameTypeTest extends MeanBeanConfiguration{

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