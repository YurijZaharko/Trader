package project.service.implementation;

import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import project.service.MailService;


public class CustomMailSenderImpl implements MailService {
    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    @Override
    public void sendEmail(String[] to, String[] cc, String subject, String text) {
        SimpleMailMessage simpleMailMessage = init(subject, text);
        simpleMailMessage.setTo(to);
        if (cc != null) {
            simpleMailMessage.setCc(cc);
        }
        send(simpleMailMessage);
    }

    @Async
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = init(subject, text);
        simpleMailMessage.setTo(to);
        send(simpleMailMessage);
    }

    private SimpleMailMessage init(String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(this.simpleMailMessage);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }

    private void send(SimpleMailMessage simpleMailMessage) {
        try {
            mailSender.send(simpleMailMessage);
        } catch (MailSendException e) {
            System.err.println(e.getMessage());
        }
    }


    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage template) {
        this.simpleMailMessage = template;
    }
}
