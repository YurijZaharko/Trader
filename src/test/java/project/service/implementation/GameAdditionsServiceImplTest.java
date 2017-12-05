package project.service.implementation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import project.entity.GameAdditions;
import project.form.GameAdditionsForm;
import project.form.populator.GameAdditionsFormPopulator;
import project.repository.GameAdditionsRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameAdditionsServiceImplTest {
    private Long idExpected = 15L;
    @Mock
    private GameAdditionsRepository gameAdditionsRepository;
    @Mock
    private GameAdditionsFormPopulator gameAdditionsFormPopulator;
    @InjectMocks
    private GameAdditionsServiceImpl unit;
    @Mock
    private Pageable pageable;
    @Mock
    private GameAdditionsForm gameAdditionsForm;
    @Mock
    private GameAdditions gameAdditionsExpected;

    @Test
    public void findAll() throws Exception {
        //given
        //when
        unit.findAll();
        //then
        verify(gameAdditionsRepository, atLeastOnce()).findAll();
    }

    @Test
    public void findAllPageable() throws Exception {
        //given
        //when
        unit.findAll(pageable);
        //then
        verify(gameAdditionsRepository, atLeastOnce()).findAll(any(Pageable.class));
    }

    @Test
    public void save() throws Exception {
        //given
        when(gameAdditionsFormPopulator.convertFormEntity(any(GameAdditionsForm.class))).thenReturn(gameAdditionsExpected);
        ArgumentCaptor<GameAdditions> captor = ArgumentCaptor.forClass(GameAdditions.class);
        //when
        unit.save(gameAdditionsForm);
        //then
        verify(gameAdditionsRepository, atLeastOnce()).save(captor.capture());
        GameAdditions gameAdditionsActual = captor.getValue();
        Assert.assertEquals(gameAdditionsExpected, gameAdditionsActual);
    }

    @Test
    public void findOne() throws Exception {
        //given
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        unit.findOne(idExpected);
        //then
        verify(gameAdditionsRepository, atLeastOnce()).findOne(captor.capture());
        Long idActual = captor.getValue();
        Assert.assertEquals(idExpected, idActual);
    }

    @Test
    public void findByGameAdditionsName() throws Exception {
        //given
        String textExpected = "TEXT";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //when
        unit.findByGameAdditionsName(textExpected);
        //then
        verify(gameAdditionsRepository, atLeastOnce()).findByGameAdditionsName(captor.capture());
        String textActual = captor.getValue();
        Assert.assertTrue(textExpected.equals(textActual));
    }

    @Test
    public void findForGameAdditionsForm() throws Exception {
        //given
        when(gameAdditionsRepository.findOne(idExpected)).thenReturn(gameAdditionsExpected);
        ArgumentCaptor<GameAdditions> captor = ArgumentCaptor.forClass(GameAdditions.class);
        //when
        unit.findForGameAdditionsForm(idExpected);
        //then
        verify(gameAdditionsFormPopulator, atLeastOnce()).convertEntityForm(captor.capture());
        GameAdditions gameAdditionsActual = captor.getValue();
        Assert.assertEquals(gameAdditionsExpected, gameAdditionsActual);
    }

    @Test
    public void deleteById() throws Exception {
        //given
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        unit.deleteById(idExpected);
        //then
        verify(gameAdditionsRepository, atLeastOnce()).delete(captor.capture());
        Long idActual = captor.getValue();
        Assert.assertEquals(idExpected, idActual);
    }
}