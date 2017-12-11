package project.service.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import project.entity.GameType;
import project.service.GameTypeService;

import java.util.LinkedList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class IndexServiceImplTest {
    private List<GameType> gameTypeList;
    private List<GameType> listExpected;
    @Mock
    private GameTypeService gameTypeService;
    @InjectMocks
    private IndexServiceImpl unit;

    @Before
    public void setUp() throws Exception {
        GameType gameType_1 = new GameType();
        GameType gameType_2 = new GameType();
        GameType gameType_3 = new GameType();
        GameType gameType_4 = new GameType();
        GameType gameType_5 = new GameType();
        GameType gameType_6 = new GameType();
        gameType_1.setGameName("gameType_1");
        gameType_2.setGameName("gameType_2");
        gameType_3.setGameName("gameType_3");
        gameType_4.setGameName("gameType_4");
        gameType_5.setGameName("gameType_5");
        gameType_6.setGameName("gameType_6");

        gameTypeList = new LinkedList<>();
        gameTypeList.add(gameType_1);
        gameTypeList.add(gameType_2);
        gameTypeList.add(gameType_3);
        gameTypeList.add(gameType_4);
        gameTypeList.add(gameType_5);
        gameTypeList.add(gameType_6);

        listExpected = new LinkedList<>();
        listExpected.add(gameType_1);
        listExpected.add(gameType_3);
        listExpected.add(gameType_5);
        listExpected.add(gameType_6);
        listExpected.add(gameType_2);
        listExpected.add(gameType_4);
    }

    @Test
    public void findAll() throws Exception {
        //given
        Mockito.when(gameTypeService.findAllFetchAll()).thenReturn(gameTypeList);
        //when
        List<GameType> listActual = unit.findAll();
        //then
        Assert.assertEquals(listExpected, listActual);
    }
}