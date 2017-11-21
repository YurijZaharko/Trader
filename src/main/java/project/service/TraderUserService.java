package project.service;

import project.form.RegistrationForm;


public interface TraderUserService {

    void saveRegistrationForm(RegistrationForm registrationForm);
    String findHelpPage(String linkName);
}
