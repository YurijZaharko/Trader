package project.events.eventListener;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import project.events.SendVerificationEvent;
import project.service.MailService;

@RunWith(MockitoJUnitRunner.class)
public class SendVerificationListenerTest {
    @Mock
    private MailService mailService;

    @InjectMocks
    private SendVerificationListener unit;

    @Mock
    private SendVerificationEvent sendVerificationEvent;

    private String textBefore = "before";
    private String subjectExpected = "subjectExpected";
    @Before
    public void setUp() throws Exception {

        unit.setSubject(subjectExpected);
        unit.setTextBefore(textBefore);
    }

    @Test
    public void onApplicationEvent() throws Exception {
        //given
        String verificationKeyExpected = "key";
        String emailToExpected = "to";
        String textExpected = textBefore + verificationKeyExpected + "\"/>";
        ArgumentCaptor<String> captorEmail = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captorSubject = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captorText = ArgumentCaptor.forClass(String.class);
        Mockito.when(sendVerificationEvent.getVerificationKey()).thenReturn(verificationKeyExpected);
        Mockito.when(sendVerificationEvent.getTo()).thenReturn(emailToExpected);
        //when
        unit.onApplicationEvent(sendVerificationEvent);
        //then
        Mockito.verify(mailService, Mockito.atLeastOnce()).sendEmail(captorEmail.capture(), captorSubject.capture(), captorText.capture());
        String emailActual = captorEmail.getValue();
        String subjectActual = captorSubject.getValue();
        String textActual = captorText.getValue();
        Assert.assertEquals(emailToExpected, emailActual);
        Assert.assertEquals(subjectExpected, subjectActual);
        Assert.assertEquals(textExpected, textActual);
    }

}