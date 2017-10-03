package project.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.form.RegistrationForm;

import java.util.regex.Pattern;


public class RegistrationValidator implements Validator {

    private String emailPattern;

    private String nickNamePattern;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm registrationForm = (RegistrationForm) o;
        Pattern email = Pattern.compile(emailPattern);
        Pattern nickName = Pattern.compile(nickNamePattern);

        if (!email.matcher(registrationForm.getEmail()).matches()) {
            errors.rejectValue("email", "", "Email must contain only a-z A-Z 0-9 _ + * / -");
        }

        if (!nickName.matcher(registrationForm.getNickName()).matches()) {
            errors.rejectValue("nickName", "", "Name must contain only a-z A-Z 0-9 _ + * / -");
        }
        if (!registrationForm.getPassword().equals(registrationForm.getPasswordRepeat())) {
            errors.rejectValue("password", "", "Passwords didn't match");
            errors.rejectValue("passwordRepeat", "", "Passwords didn't match");
        }

        String emptyError = "Can't be empty";
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", emptyError);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickName", "", emptyError);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", emptyError);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordRepeat", "", emptyError);
    }

    public void setEmailPattern(String emailPattern) {
        this.emailPattern = emailPattern;
    }

    public void setNickNamePattern(String nickNamePattern) {
        this.nickNamePattern = nickNamePattern;
    }
}
