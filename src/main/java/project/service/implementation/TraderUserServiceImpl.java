package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.entity.TemplateLink;
import project.entity.TraderUser;
import project.entity.VerificationToken;
import project.entity.enumtype.Role;
import project.events.eventPublisher.SendVerificationPublisher;
import project.form.RegistrationForm;
import project.form.populator.RegistrationFormPopulator;
import project.repository.TemplateLinkRepository;
import project.repository.TraderUserRepository;
import project.repository.VerificationTokenRepository;
import project.service.StringFileReader;
import project.service.TraderUserService;
import project.service.VerificationTokenService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service(value = "userDetailsService")
public class TraderUserServiceImpl implements TraderUserService, UserDetailsService {
    private TraderUserRepository traderUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RegistrationFormPopulator registrationFormPopulator;
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationTokenService verificationTokenService;
    private SendVerificationPublisher sendVerificationPublisher;
    private TemplateLinkRepository templateLinkRepository;
    private StringFileReader stringFileReader;
    private File templateDirectory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return traderUserRepository.findByUsername(username);
    }

    @PostConstruct
    private void createDefaultAdmin() {
        TraderUser traderUser = traderUserRepository.findOne(1L);
        if (traderUser == null) {
            traderUser = new TraderUser();
            traderUser.setId(1L);
            traderUser.setUsername("scipgames@gmail.com");
            traderUser.setNickName("Scip");
            traderUser.setRole(Role.ROLE_ADMIN);
            traderUser.setEnabled(true);
            traderUser.setPassword(bCryptPasswordEncoder.encode("nimda"));
            traderUser.setAccountNonLocked(true);
            traderUser.setRegistrationTime(Calendar.getInstance());
            traderUserRepository.save(traderUser);
        }
    }

    @Override
    public void saveRegistrationForm(RegistrationForm registrationForm) {
        TraderUser traderUser = registrationFormPopulator.convertFormUser(registrationForm);
        setRoleAndLockStatus(traderUser);
        VerificationToken verificationToken = generateVerificationToken(registrationForm);
        verificationTokenRepository.saveAndFlush(verificationToken);
        traderUser.setVerificationToken(verificationToken);
        traderUserRepository.save(traderUser);
        sendActivationURL(verificationToken.getVerificationKey(), registrationForm.getEmail());
    }

    private void setRoleAndLockStatus(TraderUser traderUser){
        traderUser.setRole(Role.ROLE_UNACTIVATED_USER);
        traderUser.setAccountNonLocked(true);
    }

    private VerificationToken generateVerificationToken(RegistrationForm registrationForm){
        Calendar currentTime = Calendar.getInstance();
        String currentDate = getCurrentDate(currentTime);
        Calendar expirationDate = generateExpirationDate(currentTime);

        String verificationKey = verificationTokenService.verificationKeyGenerator(registrationForm.getEmail(), registrationForm.getNickName(), currentDate);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setVerificationKey(verificationKey);
        verificationToken.setExpirationDate(expirationDate);
        return verificationToken;
    }

    private String getCurrentDate(Calendar date){
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH-mm-ss");
        return simpleDateFormat.format(date.getTime());
    }

    private Calendar generateExpirationDate(Calendar currentTime) {
        int expireHours = 24;
        currentTime.add(Calendar.HOUR_OF_DAY, expireHours);
        return currentTime;
    }

    private void sendActivationURL(String verificationKey, String userEmail) {
        sendVerificationPublisher.publish(verificationKey, userEmail);
    }

    @Override
    public String findHelpPage(String linkName) {
        /* TODO: move method findHelpPage(String string) to separate class */
        TemplateLink templateLink = templateLinkRepository.findByLinkName(linkName);
        String templateName = templateLink.getTemplateName();
        String content;
        String extension = ".txt";
        try {
            content = stringFileReader.readFromFile(templateDirectory, templateName + extension);
        } catch (IOException e) {
            e.printStackTrace();
            content = "";
        }
        return content;
    }

    @Override
    public TraderUser findByUsername(String username) {
        return traderUserRepository.findByUsername(username);
    }

    @Autowired
    public void setTraderUserRepository(TraderUserRepository traderUserRepository) {
        this.traderUserRepository = traderUserRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRegistrationFormPopulator(RegistrationFormPopulator registrationFormPopulator) {
        this.registrationFormPopulator = registrationFormPopulator;
    }

    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Autowired
    public void setVerificationTokenService(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    @Autowired
    public void setSendVerificationPublisher(SendVerificationPublisher sendVerificationPublisher) {
        this.sendVerificationPublisher = sendVerificationPublisher;
    }

    @Autowired
    public void setTemplateLinkRepository(TemplateLinkRepository templateLinkRepository) {
        this.templateLinkRepository = templateLinkRepository;
    }

    @Autowired
    public void setStringFileReader(StringFileReader stringFileReader) {
        this.stringFileReader = stringFileReader;
    }

    @Autowired
    public void setTemplateDirectory(File templateDirectory) {
        this.templateDirectory = templateDirectory;
    }
}
