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
import project.entity.TraderUser;
import project.entity.VerificationToken;
import project.events.eventPublisher.SendVerificationPublisher;
import project.form.RegistrationForm;
import project.form.populator.RegistrationFormPopulator;
import project.repository.TraderUserRepository;
import project.repository.VerificationTokenRepository;
import project.service.VerificationTokenService;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TraderUserServiceImplTest {
    private TraderUser traderUserExpected;
    private RegistrationForm registrationForm;

    @Mock
    private SendVerificationPublisher sendVerificationPublisher;
    @Mock
    private TraderUserRepository traderUserRepository;
    @Mock
    private RegistrationFormPopulator registrationFormPopulator;
    @Mock
    private VerificationTokenRepository verificationTokenRepository;
    @InjectMocks
    private TraderUserServiceImpl unit;

    @Before
    public void setUp() throws Exception {
        String emailExpected = "mail@mail.mal";
        String passwordExpected = "pass";
        String nickNameExpected = "nick";
        traderUserExpected = new TraderUser();
        traderUserExpected.setUsername(emailExpected);
        traderUserExpected.setPassword(passwordExpected);
        traderUserExpected.setNickName(nickNameExpected);

        registrationForm = new RegistrationForm();
        registrationForm.setEmail(emailExpected);
        registrationForm.setNickName(nickNameExpected);
        registrationForm.setPassword(passwordExpected);
        registrationForm.setPasswordRepeat(passwordExpected);
    }

    @Test
    public void loadUserByUsername() throws Exception {
        //given
        String userNameExpected = "someUser";
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //when
        unit.findByUsername(userNameExpected);
        //then
        Mockito.verify(traderUserRepository, Mockito.atLeastOnce()).findByUsername(captor.capture());
        String userNameActual = captor.getValue();
        assertTrue(userNameExpected.equals(userNameActual));
    }

    @Test
    public void saveRegistrationForm() throws Exception {
        //given
        VerificationTokenService verificationTokenService = new VerificationTokenImpl();
        unit.setVerificationTokenService(verificationTokenService);
        when(registrationFormPopulator.convertFormUser(any(RegistrationForm.class))).thenReturn(traderUserExpected);
        ArgumentCaptor<TraderUser> captor = ArgumentCaptor.forClass(TraderUser.class);
        //when
        unit.saveRegistrationForm(registrationForm);
        //then
        verify(verificationTokenRepository, atLeastOnce()).saveAndFlush(any(VerificationToken.class));
        verify(traderUserRepository, atLeastOnce()).save(captor.capture());
        TraderUser traderUserActual = captor.getValue();
        Assert.assertTrue(traderUserExpected.equals(traderUserActual));
        verify(sendVerificationPublisher, atLeastOnce()).publish(anyString(), anyString());

    }

    @Test
    public void findHelpPage() throws Exception {
        //given
        //when
        //then
    }

    @Test
    public void findByUsername() throws Exception {
        //given
        //when
        //then
    }
}