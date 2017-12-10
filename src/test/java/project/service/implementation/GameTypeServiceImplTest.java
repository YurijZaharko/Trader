package project.service.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.form.populator.GameTypeFormPopulator;
import project.repository.GameTypeRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class GameTypeServiceImplTest {
    private GameType gameType;
    private Long idExpected = 25L;
    @Mock
    private GameTypeRepository gameTypeRepository;
    @Mock
    private GameTypeFormPopulator gameTypeFormPopulator;
    @InjectMocks
    private GameTypeServiceImpl unit;
    @Mock
    private Pageable pageable;
    @Mock
    private GameTypeForm gameTypeForm;

    @Before
    public void setUp() throws Exception {
        gameType = new GameType();
        gameType.setId(idExpected);
    }

    @Test
    public void findAll() throws Exception {
        //given
        //when
        unit.findAll(pageable);
        //then
        verify(gameTypeRepository).findAll(any(Pageable.class));
    }

    @Test
    public void saveGameTypeForm() throws Exception {
        //given
        Mockito.when(gameTypeFormPopulator.convertFormEntity(any(GameTypeForm.class))).thenReturn(gameType);
        ArgumentCaptor<GameType> captor = ArgumentCaptor.forClass(GameType.class);
        //when
        unit.saveGameTypeForm(gameTypeForm);
        //then
        verify(gameTypeRepository, atLeastOnce()).saveAndFlush(captor.capture());
        GameType value = captor.getValue();
        Long idActual = value.getId();
        Assert.assertEquals(idExpected, idActual);
    }

    @Test
    public void deleteById() throws Exception {
        //given
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        //when
        unit.deleteById(idExpected);
        //then
        verify(gameTypeRepository, atLeast(1)).delete(captor.capture());
        Long idActual = captor.getValue();
        Assert.assertEquals(idExpected, idActual);
    }

    @Test
    public void findForForm() throws Exception {
        //given
        when(gameTypeRepository.findOneFetchAll(idExpected)).thenReturn(gameType);
        ArgumentCaptor<GameType> captor = ArgumentCaptor.forClass(GameType.class);
        //when
        unit.findForForm(idExpected);
        //then
        verify(gameTypeFormPopulator, atLeastOnce()).convertEntityForm(captor.capture());
        GameType value = captor.getValue();
        Long idActual = value.getId();
        Assert.assertEquals(idExpected, idActual);
    }

    @Test
    public void findAllFetchAll() throws Exception {
        //given
        //when
        unit.findAllFetchAll();
        //then
        verify(gameTypeRepository, atLeastOnce()).findAllFetchAll();
    }
}