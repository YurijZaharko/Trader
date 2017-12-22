package project.form.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import project.form.RegistrationForm;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationValidatorTest {
    private RegistrationValidator unit;


    ;

    @Before
    public void setUp() throws Exception {
        unit = new RegistrationValidator();
        String emailPattern = "^\\w{3,15}@{1}\\w{3}.{1}\\w{3}$";
        String nickNamePattern = "^\\D{3,15}$";
        unit.setEmailPattern(emailPattern);
        unit.setNickNamePattern(nickNamePattern);
    }

    @Test
    public void supports() throws Exception {
        Assert.assertTrue(unit.supports(RegistrationForm.class));
    }

    @Test
    public void validateWithErrors() throws Exception {
        //given
        int errorCountExpected = 4;
        RegistrationForm registrationFormWithErrors = new RegistrationForm();
        Errors errors = new BeanPropertyBindingResult(registrationFormWithErrors, "testName");

        registrationFormWithErrors.setEmail("asd");
        registrationFormWithErrors.setPassword("aa");
        registrationFormWithErrors.setPasswordRepeat("aabb");
        registrationFormWithErrors.setNickName("bb");
        //when
        unit.validate(registrationFormWithErrors, errors);
        //then
        Assert.assertTrue(errors.hasErrors());
        int errorCountActual = errors.getErrorCount();
        Assert.assertEquals(errorCountExpected, errorCountActual);
    }

    @Test
    public void validateNoErrors() throws Exception {
        //given
        RegistrationForm registrationFormNoErrors = new RegistrationForm();
        Errors errors = new BeanPropertyBindingResult(registrationFormNoErrors, "test");

        registrationFormNoErrors.setEmail("correctEmail@mal.mal");
        registrationFormNoErrors.setPassword("correctPassword");
        registrationFormNoErrors.setPasswordRepeat("correctPassword");
        registrationFormNoErrors.setNickName("correctName");
        //when
        unit.validate(registrationFormNoErrors, errors);
        //then
        Assert.assertFalse(errors.hasErrors());
    }
}