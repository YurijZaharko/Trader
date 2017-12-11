package project.service;

public interface MailService {
    void sendEmail(String[] to, String[] cc, String subject, String text);
    void sendEmail(String to, String subject, String text);
}
