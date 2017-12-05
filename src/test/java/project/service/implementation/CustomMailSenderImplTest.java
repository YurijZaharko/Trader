package project.service.implementation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class CustomMailSenderImplTest {
    private String[] toArrayExpected = new String[]{"one", "two", "three"};
    private String[] ccExpected = new String[]{"one", "two"};
    private String subjectExpected = "sub";
    private String textExpected = "sample";

    @Mock
    private MailSender mailSender;
    @Mock
    private SimpleMailMessage template;
    @InjectMocks
    private CustomMailSenderImpl unit;

    @Test
    public void sendEmailWithCC() throws Exception {
        //given
        String fromExpected = "tradersender@gmail.com";
        Mockito.when(template.getFrom()).thenReturn(fromExpected);
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        //when
        unit.sendEmail(toArrayExpected, ccExpected, subjectExpected, textExpected);
        //then
        Mockito.verify(mailSender, Mockito.atLeastOnce()).send(captor.capture());
        SimpleMailMessage value = captor.getValue();
        String[] toActual = value.getTo();
        String[] ccActual = value.getCc();
        String subjectActual = value.getSubject();
        String textActual = value.getText();
        String fromActual = value.getFrom();
        Assert.assertTrue(Arrays.equals(toArrayExpected, toActual));
        Assert.assertTrue(Arrays.equals(ccExpected, ccActual));
        Assert.assertTrue(subjectExpected.equals(subjectActual));
        Assert.assertTrue(textExpected.equals(textActual));
        Assert.assertTrue(fromExpected.equals(fromActual));
    }

    @Test
    public void sendEmailWithException() throws Exception {
        //given
        Mockito.doThrow(new MailSendException("")).when(this.mailSender).send(Mockito.any(SimpleMailMessage.class));
        //when
        unit.sendEmail(toArrayExpected, ccExpected, subjectExpected, textExpected);
        //then
    }

    @Test
    public void sendEmail() throws Exception {
        //given
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        //when
        String toExpected = "to";
        unit.sendEmail(toExpected, subjectExpected, textExpected);
        //then
        Mockito.verify(mailSender, Mockito.atLeastOnce()).send(captor.capture());
        SimpleMailMessage value = captor.getValue();
        String[] to = value.getTo();
        Assert.assertTrue(to.length == 1);
        String toActual = to[0];
        Assert.assertTrue(toExpected.equals(toActual));
    }
}