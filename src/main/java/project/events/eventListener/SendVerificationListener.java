package project.events.eventListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import project.events.SendVerificationEvent;
import project.service.MailService;

import javax.annotation.Resource;


@PropertySource("classpath:spring/properties/mail-template.properties")
public class SendVerificationListener implements ApplicationListener<SendVerificationEvent> {

    private MailService mailService;

    private String textBefore;

    private String subject;

    @Override
    public void onApplicationEvent(SendVerificationEvent sendVerificationEvent) {
        String verificationKey = sendVerificationEvent.getVerificationKey();

        String emailTo = sendVerificationEvent.getTo();
        String text = textBefore + verificationKey;

        mailService.sendEmail(emailTo, subject, text);
    }

    @Resource(name = "customMailSenderImpl")
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Value("${mail-template.textBefore}")
    public void setTextBefore(String textBefore) {
        this.textBefore = textBefore;
    }
    @Value("${mail-template.subject}")
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
