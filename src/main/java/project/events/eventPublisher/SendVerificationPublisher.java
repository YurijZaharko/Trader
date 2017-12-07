package project.events.eventPublisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import project.events.SendVerificationEvent;

public class SendVerificationPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String key, String email) {
        SendVerificationEvent event = new SendVerificationEvent(applicationEventPublisher);
        event.setVerificationKey(key);
        event.setTo(email);
        applicationEventPublisher.publishEvent(event);
    }

    public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {
        this.applicationEventPublisher = publisher;
    }
}
