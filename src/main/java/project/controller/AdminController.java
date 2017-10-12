package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.form.TextForm;
import project.service.HelpService;

@Controller
public class AdminController {
    private HelpService helpService;

    @ModelAttribute("textForm")
    public TextForm getTextForm() {
        return new TextForm();
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping(value = "admin/help/faq")
    public String getFaq(Model model) {
        model.addAttribute("listFiles", helpService.getListOfTemplate());
        return "faq";
    }

    @GetMapping(value = "/admin/showFile/{fileName}")
    public String showFile(Model model,
                           @ModelAttribute(value = "textForm") TextForm textForm,
                           @PathVariable("fileName") String fileName) {
        model.addAttribute("textForm", helpService.showFile(fileName, textForm));
        model.addAttribute("listFiles", helpService.getListOfTemplate());
        return "faq";
    }

    @RequestMapping(value = "/admin/faq/save", method = RequestMethod.POST)
    public String saveTemplate(@ModelAttribute(value = "textForm") TextForm textForm) {
        helpService.saveTextFormToFile(textForm);
        return "redirect:admin/help/faq";
    }

    @Autowired
    public void setHelpService(HelpService helpService) {
        this.helpService = helpService;
    }
}
