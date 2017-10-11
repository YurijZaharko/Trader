package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.form.TextForm;
import project.service.HelpService;

@Controller(value = "/admin")
public class AdminHelpController {

    private HelpService helpService;

    @GetMapping(value = "/help/faq")
    public String getFaq(Model model){
        model.addAttribute("listFiles", helpService.getListOfTemplate());
        return "faq";
    }

    @GetMapping(value = "/showFile/{fileName}")
    public String showFile(Model model,
                           @ModelAttribute(value = "textForm") TextForm textForm,
                           @PathVariable("fileName") String fileName){
        model.addAttribute("textForm", helpService.showFile(fileName, textForm));
        model.addAttribute("listFiles", helpService.getListOfTemplate());
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

    @GetMapping(name = "/setup")
    public String getSetup(){
        return "setup";
    }

    @Autowired
    public void setHelpService(HelpService helpService) {
        this.helpService = helpService;
    }
}
