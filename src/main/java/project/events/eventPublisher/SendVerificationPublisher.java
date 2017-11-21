package project.events.eventPublisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import project.events.SendVerificationEvent;

public class SendVerificationPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(String key, String email) {
        SendVerificationEvent event = new SendVerificationEvent(publisher);
        event.setVerificationKey(key);
        event.setTo(email);
        publisher.publishEvent(event);
    }
}
