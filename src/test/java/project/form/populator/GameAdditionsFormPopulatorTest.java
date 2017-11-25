package project.form.populator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import project.entity.GameAdditions;
import project.form.GameAdditionsForm;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameAdditionsFormPopulatorTest {
    private Long idExpected = 10L;
    private String nameExpected = "name";

    @Mock
    private GameAdditions gameAdditions;

    @Mock
    private GameAdditionsForm gameAdditionsForm;

    @Spy
    private GameAdditionsFormPopulator unit;
    @Test
    public void convertEntityForm() throws Exception {
        //given
        when(gameAdditions.getId()).thenReturn(idExpected);
        when(gameAdditions.getGameAdditionsName()).thenReturn(nameExpected);
        //when
        GameAdditionsForm gameAdditionsForm = unit.convertEntityForm(gameAdditions);
        //then
        Long idActual = gameAdditionsForm.getGameAdditionsId();
        String nameActual = gameAdditionsForm.getGameAdditionsName();
        Assert.assertEquals(idExpected, idActual);
        Assert.assertEquals(nameExpected, nameActual);
    }

    @Test
    public void convertFormEntityNullId() throws Exception {
        //given
        when(gameAdditionsForm.getGameAdditionsId()).thenReturn(null);
        when(gameAdditionsForm.getGameAdditionsName()).thenReturn(nameExpected);
        //when
        GameAdditions gameAdditions = unit.convertFormEntity(gameAdditionsForm);
        //then
        Long idActual = gameAdditions.getId();
        String nameActual = gameAdditions.getGameAdditionsName();
        Assert.assertNull(idActual);
        Assert.assertEquals(nameExpected, nameActual);
    }

    @Test
    public void convertFormEntityNotNullId() throws Exception {
        //given
        when(gameAdditionsForm.getGameAdditionsId()).thenReturn(idExpected);
        //when
        GameAdditions gameAdditions = unit.convertFormEntity(gameAdditionsForm);
        //then
        Long idActual = gameAdditions.getId();
        Assert.assertEquals(idExpected, idActual);
    }

}