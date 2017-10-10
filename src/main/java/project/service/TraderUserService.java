package project.service;

import project.form.RegistrationForm;


public interface TraderUserService {

    void saveRegistrationForm(RegistrationForm registrationForm);
    void sendActivationURL(RegistrationForm registrationForm);
    String findHelpPage(String linkName);
}
