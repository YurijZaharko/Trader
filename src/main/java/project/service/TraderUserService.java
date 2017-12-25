package project.service;

import project.entity.TraderUser;
import project.form.RegistrationForm;


public interface TraderUserService {
    void saveRegistrationForm(RegistrationForm registrationForm);

    String findHelpPage(String linkName);

    TraderUser findByUsername(String userName);
}
