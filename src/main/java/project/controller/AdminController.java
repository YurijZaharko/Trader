package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.form.TextForm;
import project.service.FileUtilitiesService;

import java.io.IOException;

@Controller
public class AdminController {
    private FileUtilitiesService fileUtilitiesService;

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
        model.addAttribute("listFiles", fileUtilitiesService.getListOfTemplate());
        return "faq";
    }

    @GetMapping(value = "/admin/showFile/{fileName}")
    public String showFile(Model model,
                           @PathVariable("fileName") String fileName) {
        model.addAttribute("textForm", fileUtilitiesService.showFile(fileName));
        model.addAttribute("listFiles", fileUtilitiesService.getListOfTemplate());
        return "faq";
    }

    @RequestMapping(value = "/admin/faq/save", method = RequestMethod.POST)
    public String saveTemplate(@ModelAttribute(value = "textForm") TextForm textForm) {
        try {
            fileUtilitiesService.saveTextFormToFile(textForm);
        } catch (IOException e) {
            //TODO: add custom exception and redirect on error page

        }
        return "redirect:/admin/help/faq";
    }

    @Autowired
    public void setFileUtilitiesService(FileUtilitiesService fileUtilitiesService) {
        this.fileUtilitiesService = fileUtilitiesService;
    }
}
