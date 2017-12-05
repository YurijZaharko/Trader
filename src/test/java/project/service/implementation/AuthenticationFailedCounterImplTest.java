package project.service.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import project.entity.TraderUser;
import project.repository.TraderUserRepository;
import project.service.TraderUserService;

import java.util.Calendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFailedCounterImplTest {
    @Mock
    private TraderUserService traderUserService;

    @Mock
    private TraderUserRepository traderUserRepository;

    @InjectMocks
    private AuthenticationFailedCounterImpl unit;

    @Spy
    private TraderUser traderUser;

    @Before
    public void setUp() {
        when(traderUserService.findByUsername(anyString())).thenReturn(traderUser);
    }

    @Test
    public void checkUserOverLimitFailAttempts() {
        //given
        int overLimitFailAttempts = 5;
        when(traderUser.getFailAttempts()).thenReturn(overLimitFailAttempts);
        when(traderUser.getFirstTimeOfWrongLogin()).thenReturn(Calendar.getInstance());
        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        //when
        unit.checkUser(anyString());
        //then
        Mockito.verify(traderUser).setAccountNonLocked(captor.capture());
        Boolean actual = captor.getValue();
        Assert.assertFalse(actual);
        verify(traderUserRepository).save(any(TraderUser.class));
    }

    @Test
    public void checkUserThirdFailAttempts() {
        //given
        int thirdFailAttempts = 3;
        when(traderUser.getFailAttempts()).thenReturn(thirdFailAttempts);
        when(traderUser.getFirstTimeOfWrongLogin()).thenReturn(Calendar.getInstance());
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        //when
        unit.checkUser(anyString());
        //then
        Mockito.verify(traderUser).setFailAttempts(captor.capture());
        Integer actual = captor.getValue();
        Integer expected = ++thirdFailAttempts;
        Assert.assertEquals(expected, actual);
        verify(traderUserRepository).save(any(TraderUser.class));
    }
    @Test
    public void checkUserFirstFailLogin(){
        //given
        int failAttemptsExpected = 1;
        int differenceCouldBe = 60;
        ArgumentCaptor<TraderUser> captor = ArgumentCaptor.forClass(TraderUser.class);
        //when
        unit.checkUser(Mockito.anyString());
        //then

        verify(traderUserRepository).save(captor.capture());
        TraderUser value = captor.getValue();
        int failAttemptsActual = value.getFailAttempts();
        Calendar firstTimeOfWrongLoginActual = value.getFirstTimeOfWrongLogin();

        Assert.assertEquals(failAttemptsExpected, failAttemptsActual);
        int differenceActual = firstTimeOfWrongLoginActual.compareTo(Calendar.getInstance());
        Assert.assertTrue(differenceCouldBe > differenceActual);

    }
}