package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.form.RegistrationForm;
import project.form.validator.RegistrationValidator;
import project.service.TraderUserService;

import javax.validation.Valid;

@Controller
public class MainController {

    private TraderUserService traderUserService;

    private RegistrationValidator registrationValidator;

    @InitBinder("registrationForm")
    protected void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(registrationValidator);
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm getRegistrationForm() {
        return new RegistrationForm();
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveRegistrationData(@ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            registrationForm.setPassword("");
            registrationForm.setPasswordRepeat("");
            return "registration";
        }
        traderUserService.saveRegistrationForm(registrationForm);
        return "redirect:/";
    }

    @GetMapping("/help/{linkName}")
    public String getHelp(@PathVariable("linkName") String linkName, Model model){
        model.addAttribute("mainText", traderUserService.findHelpPage(linkName));
        return "help";
    }



    @Autowired
    public void setTraderUserService(TraderUserService traderUserService) {
        this.traderUserService = traderUserService;
    }
    @Autowired
    public void setRegistrationValidator(RegistrationValidator registrationValidator) {
        this.registrationValidator = registrationValidator;
    }
}
