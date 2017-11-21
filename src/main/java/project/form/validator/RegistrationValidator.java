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
        emailMatcher(errors,registrationForm);
        nickNameMatcher(errors, registrationForm);
        passwordMatcher(errors,registrationForm);
        validateEmptyOrWhitespace(errors);
    }

    private void emailMatcher(Errors errors, RegistrationForm registrationForm){
        Pattern emailCompilePattern = Pattern.compile(emailPattern);
        String email = registrationForm.getEmail();
        if (!emailCompilePattern.matcher(email).matches()) {
            errors.rejectValue("email", "", "Email must contain only a-z A-Z 0-9 _ + * / -");
        }
    }

    private void nickNameMatcher(Errors errors, RegistrationForm registrationForm){
        Pattern nickNameCompilePattern = Pattern.compile(nickNamePattern);
        String nickName = registrationForm.getNickName();
        if (!nickNameCompilePattern.matcher(nickName).matches()) {
            errors.rejectValue("nickName", "", "Name must contain only a-z A-Z 0-9 _ + * / -");
        }
    }

    private void passwordMatcher(Errors errors,RegistrationForm registrationForm){
        if (isPasswordsNotEquals(registrationForm)) {
            errors.rejectValue("password", "", "Passwords didn't match");
            errors.rejectValue("passwordRepeat", "", "Passwords didn't match");
        }
    }

    private boolean isPasswordsNotEquals(RegistrationForm registrationForm){
        return !registrationForm.getPassword().equals(registrationForm.getPasswordRepeat());
    }


    private void validateEmptyOrWhitespace(Errors errors){
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
