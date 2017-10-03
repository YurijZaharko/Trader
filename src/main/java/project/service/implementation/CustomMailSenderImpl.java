package project.service.implementation;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import project.service.MailService;


public class CustomMailSenderImpl implements MailService {

    private MailSender mailSender;

    private SimpleMailMessage template;

    @Override
    public void sendEmail(String[] to, String[] cc, String subject, String text) {
        SimpleMailMessage simpleMailMessage = init( subject, text);
        simpleMailMessage.setTo(to);
        if (cc != null){
            simpleMailMessage.setCc(cc);
        }
        send(simpleMailMessage);
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = init(subject, text);
        simpleMailMessage.setTo(to);
        send(simpleMailMessage);
    }

    private SimpleMailMessage init(String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(this.template);

        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        return simpleMailMessage;
    }

    private void send(SimpleMailMessage simpleMailMessage){
        try {
            mailSender.send(simpleMailMessage);
        }catch (MailException e){
            System.err.println(e.getMessage());
        }
    }


    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage template) {
        this.template = template;
    }
}
