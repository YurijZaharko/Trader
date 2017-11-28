package project.form.populator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.entity.TraderUser;
import project.form.RegistrationForm;

public class RegistrationFormPopulator {

    private BCryptPasswordEncoder BCryptPasswordEncoder;

    public TraderUser convertFormUser(RegistrationForm registrationForm) {
        TraderUser newUser = new TraderUser();
        newUser.setNickName(registrationForm.getNickName());
        newUser.setUsername(registrationForm.getEmail());
        newUser.setPassword(BCryptPasswordEncoder.encode(registrationForm.getPassword()));
        return newUser;
    }


    public void setBCryptPasswordEncoder(BCryptPasswordEncoder BCryptPasswordEncoder) {
        this.BCryptPasswordEncoder = BCryptPasswordEncoder;
    }
}
