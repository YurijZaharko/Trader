package project.events.eventPublisher;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import project.events.SendVerificationEvent;

@RunWith(MockitoJUnitRunner.class)
public class SendVerificationPublisherTest {
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private SendVerificationPublisher unit;

    @Test
    public void publish() throws Exception {
        //given
        String keyExpected = "key";
        String emailExpected = "email";
        ArgumentCaptor<SendVerificationEvent> captor = ArgumentCaptor.forClass(SendVerificationEvent.class);
        //when
        unit.publish(keyExpected, emailExpected);
        //then
        Mockito.verify(applicationEventPublisher, Mockito.atLeastOnce()).publishEvent(captor.capture());
        SendVerificationEvent value = captor.getValue();
        String keyActual = value.getVerificationKey();
        String emailActual = value.getTo();
        Assert.assertEquals(keyExpected, keyActual);
        Assert.assertEquals(emailExpected, emailActual);

    }

}