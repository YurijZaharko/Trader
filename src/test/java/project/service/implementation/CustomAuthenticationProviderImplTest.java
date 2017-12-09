package project.service.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.method.annotation.CsrfTokenArgumentResolver;
import project.entity.TraderUser;
import project.entity.enumtype.Role;
import project.service.TraderUserService;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomAuthenticationProviderImplTest {
    private final String principalExpected = "Login";
    private final String credentialsExpected = "myPassword";
    private final Role roleExpected = Role.ROLE_REGISTERED_USER;

    @Mock
    private TraderUserService traderUserService;
    @Mock
    private BCryptPasswordEncoder encoder;
    @InjectMocks
    private CustomAuthenticationProviderImpl unit;
    @Mock
    private Authentication authentication;
    @Mock
    private TraderUser traderUser;

    @Before
    public void setUp() throws Exception {
        when(authentication.getPrincipal()).thenReturn(principalExpected);
        when(authentication.getCredentials()).thenReturn(credentialsExpected);
        when(traderUser.getRole()).thenReturn(roleExpected);
        when(traderUser.getUsername()).thenReturn(principalExpected);
        when(traderUser.getPassword()).thenReturn(credentialsExpected);
    }

    @Test
    public void authenticateSuccessful() throws Exception {
        //given
        when(traderUserService.findByUsername(principalExpected)).thenReturn(traderUser);
        when(traderUser.isAccountNonLocked()).thenReturn(true);
        when(encoder.matches(anyString(), anyString())).thenReturn(true);
        //when
        Authentication authenticate = unit.authenticate(authentication);
        //then
        TraderUser traderUser = (TraderUser) authenticate.getPrincipal();
        String principalActual = traderUser.getUsername();
        String credentialsActual = authenticate.getCredentials() + "";
        Collection<? extends GrantedAuthority> authoritiesActual = authenticate.getAuthorities();
        int sizeActual = authoritiesActual.size();

        assertEquals(principalExpected, principalActual);
        assertEquals(credentialsExpected, credentialsActual);
        int sizeExpected = 1;
        assertEquals(sizeExpected, sizeActual);
        String roleActual = "";
        for (GrantedAuthority grantedAuthority : authoritiesActual) {
            roleActual = grantedAuthority.getAuthority();
        }
        assertEquals(roleExpected.toString(), roleActual);

    }

    @Test(expected = UsernameNotFoundException.class)
    public void userNotExist() throws Exception {
        //given
        when(traderUserService.findByUsername(principalExpected)).thenReturn(null);
        //when
        unit.authenticate(authentication);
        //then
    }

    @Test(expected = LockedException.class)
    public void authenticateAccountLock() throws Exception {
        //given
        when(traderUserService.findByUsername(principalExpected)).thenReturn(traderUser);
        when(traderUser.isAccountNonLocked()).thenReturn(false);
        //when
        unit.authenticate(authentication);
        //then
    }

    @Test(expected = BadCredentialsException.class)
    public void authenticateBadCredential() throws Exception {
        //given
        when(traderUserService.findByUsername(principalExpected)).thenReturn(traderUser);
        when(traderUser.isAccountNonLocked()).thenReturn(true);
        when(encoder.matches(anyString(), anyString())).thenReturn(false);
        //when
        unit.authenticate(authentication);
        //then
    }

    @Test
    public void supportsUsernamePasswordAuthenticationToken() throws Exception {
        //when
        boolean actual = unit.supports(UsernamePasswordAuthenticationToken.class);
        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void notSupportOtherClazz() throws Exception {
        //when
        boolean actual = unit.supports(CsrfTokenArgumentResolver.class);
        //then
        Assert.assertFalse(actual);
    }
}