package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.form.TextForm;
import project.service.FileUtilitiesService;

@Controller
public class AdminController {
    private FileUtilitiesService FileUtilitiesService;

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
        model.addAttribute("listFiles", FileUtilitiesService.getListOfTemplate());
        return "faq";
    }

    @GetMapping(value = "/admin/showFile/{fileName}")
    public String showFile(Model model,
                           @PathVariable("fileName") String fileName) {
        model.addAttribute("textForm", FileUtilitiesService.showFile(fileName));
        model.addAttribute("listFiles", FileUtilitiesService.getListOfTemplate());
        return "faq";
    }

    @RequestMapping(value = "/admin/faq/save", method = RequestMethod.POST)
    public String saveTemplate(@ModelAttribute(value = "textForm") TextForm textForm) {
        FileUtilitiesService.saveTextFormToFile(textForm);
        return "redirect:admin/help/faq";
    }

    @Autowired
    public void setFileUtilitiesService(FileUtilitiesService fileUtilitiesService) {
        this.FileUtilitiesService = fileUtilitiesService;
    }
}
