package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.entity.TraderUser;
import project.entity.VerificationToken;
import project.entity.role.Role;
import project.events.eventPublisher.SendVerificationPublisher;
import project.form.RegistrationForm;
import project.form.populator.RegistrationFormPopulator;
import project.repository.TraderUserRepository;
import project.repository.VerificationTokenRepository;
import project.service.TraderUserService;
import project.service.VerificationTokenService;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service(value = "userDetailsService")
public class TraderUserServiceImpl implements TraderUserService, UserDetailsService {

    private TraderUserRepository traderUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RegistrationFormPopulator registrationFormPopulator;

    private VerificationTokenRepository verificationTokenRepository;

    private VerificationTokenService verificationTokenService;

    private SendVerificationPublisher sendVerificationPublisher;

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
            traderUser.setRole(Role.ADMIN_USER);
            traderUser.setEnabled(true);
            traderUser.setPassword(bCryptPasswordEncoder.encode("nimda"));
            traderUser.setAccountNonLocked(true);
            traderUserRepository.save(traderUser);
        }
    }

    @Override
    public void saveRegistrationForm(RegistrationForm registrationForm) {
        TraderUser traderUser = registrationFormPopulator.convertFormUser(registrationForm);
        traderUser.setRole(Role.UNACTIVATED_USER);
        traderUser.setAccountNonLocked(true);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH-mm-ss");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);
        int expireHours = 24;
        Date expirationDate = generateExpirationDate(date, expireHours);

        String verificationKey = verificationTokenService.verificationKeyGenerator(registrationForm.getEmail(), registrationForm.getNickName(), currentDate);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setVerificationKey(verificationKey);
        verificationToken.setExpirationDate(expirationDate);
        verificationTokenRepository.saveAndFlush(verificationToken);

        traderUser.setVerificationToken(verificationToken);
        traderUserRepository.save(traderUser);


        sendVerificationPublisher.publish(verificationKey, registrationForm.getEmail());

    }

    private Date generateExpirationDate(Date date, int expireInHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, expireInHours);
        return calendar.getTime();
    }



    @Override
    public void sendActivationURL(RegistrationForm registrationForm) {
        String userName = registrationForm.getEmail();
        TraderUser traderUser = traderUserRepository.findByUsername(userName);

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
}
