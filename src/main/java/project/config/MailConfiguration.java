package project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:spring/properties/gmail.properties")
public class MailConfiguration {

    @Value("${gmail.protocol}")
    private String protocol;
    @Value("${gmail.host}")
    private String host;
    @Value("${gmail.port}")
    private int port;
    @Value("${gmail.smtp.socketFactory.port}")
    private int socketPort;
    @Value("${gmail.smtp.auth}")
    private boolean auth;
    @Value("${gmail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${gmail.smtp.starttls.required}")
    private boolean startllsRequired;
    @Value("${gmail.smtp.debug}")
    private boolean debug;
    @Value("${gmail.smtp.socketFactory.fallback}")
    private boolean fallback;
    @Value("${gmail.from}")
    private String from;
    @Value("${gmail.username}")
    private String username;
    @Value("${gmail.password}")
    private String password;

    @Bean(name = "javaMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailProperties.put("mail.smtp.starttls.required", startllsRequired);
        mailProperties.put("mail.smtp.socketFactory.port", socketPort);
        mailProperties.put("mail.smtp.debug", debug);
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.socketFactory.fallback", fallback);

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }
}
