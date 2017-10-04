package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.form.TextForm;
import project.service.HelpService;

@Controller(value = "/admin/help")
public class AdminHelpController {

    private HelpService helpService;

    @GetMapping(value = "/faq")
    public String getFaq(){
        return "faq";
    }

    @ModelAttribute("textForm")
    public TextForm getTextForm(){
        return new TextForm();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveTemplate(@ModelAttribute(value = "textForm") TextForm textForm){
        helpService.saveTextFormToFile(textForm);
        return "redirect:/faq";
    }

    @Autowired
    public void setHelpService(HelpService helpService) {
        this.helpService = helpService;
    }
}
