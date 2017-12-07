package project.form.populator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.entity.TraderUser;
import project.form.RegistrationForm;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationFormPopulatorTest {
    private String nameExpected = "name";
    private String emailExpected  = "email";
    private  String passwordExpected = "pass";
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private RegistrationFormPopulator unit;

    @Mock
    private RegistrationForm registrationForm;

    @Test
    public void convertFormUser() throws Exception {
        //given
        when(registrationForm.getEmail()).thenReturn(emailExpected);
        when(registrationForm.getNickName()).thenReturn(nameExpected);
        when(registrationForm.getPassword()).thenReturn("anyString");
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(passwordExpected);
        //when
        TraderUser traderUser = unit.convertFormUser(registrationForm);
        //then
        String nickNameActual = traderUser.getNickName();
        String usernameActual = traderUser.getUsername();
        String passwordActual = traderUser.getPassword();
        Assert.assertEquals(nameExpected, nickNameActual);
        Assert.assertEquals(emailExpected, usernameActual);
        Assert.assertEquals(passwordExpected,passwordActual);
    }

}