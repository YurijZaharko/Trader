package project.editors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import project.entity.GameAdditions;
import project.service.GameAdditionsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameAdditionsEditorTest {
    @Mock
    private GameAdditionsService gameAdditionsService;

    @InjectMocks
    private GameAdditionsEditor unit;

    @Mock
    private GameAdditions gameAdditions;

    @Test
    public void setAsText() throws Exception {
        //given
        final String text = "5";
        when(gameAdditionsService.findOne(Mockito.anyLong())).thenReturn(any(GameAdditions.class));
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        unit.setAsText(text);
        //then
        Mockito.verify(gameAdditionsService, atLeastOnce()).findOne(captor.capture());
        Long expected = Long.valueOf(text);
        Long actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAsText() throws Exception {
        //given
        String expected = "testName";
        unit.setValue(gameAdditions);
        when(gameAdditions.getGameAdditionsName()).thenReturn(expected);
        //when
        String actual = unit.getAsText();
        //then
        Assert.assertEquals(expected, actual);
    }

}