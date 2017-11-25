package project.events.eventListener;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import project.service.AuthenticationFailedCounterService;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationListenerTest {
    @Mock
    private AuthenticationFailedCounterService authenticationFailedCounterService;

    @Mock
    private AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent;

    @InjectMocks
    private AuthenticationListener unit;

    @Mock
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Test
    public void authenticationFailed() throws Exception {
        //given
        String expected = "name";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.when(authenticationFailureBadCredentialsEvent.getAuthentication()).thenReturn(usernamePasswordAuthenticationToken);
        Mockito.when(usernamePasswordAuthenticationToken.getPrincipal()).thenReturn(expected);
        //when
        unit.authenticationFailed(authenticationFailureBadCredentialsEvent);
        //then
        Mockito.verify(authenticationFailedCounterService, Mockito.atLeastOnce()).checkUser(captor.capture());
        String actual = captor.getValue();
        Assert.assertEquals(expected, actual);
    }

}