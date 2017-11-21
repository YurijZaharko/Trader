package project.events;

import org.springframework.context.ApplicationEvent;

public class SendVerificationEvent extends ApplicationEvent {
    private String verificationKey;
    private String sendTo;

    public SendVerificationEvent(Object source) {
        super(source);
    }

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    public String getTo() {
        return sendTo;
    }

    public void setTo(String sendTo) {
        this.sendTo = sendTo;
    }
}
