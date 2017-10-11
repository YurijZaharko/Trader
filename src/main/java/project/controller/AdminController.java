package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.form.GameTypeForm;
import project.form.TextForm;
import project.service.CountryService;
import project.service.GameAdditionsService;
import project.service.GameTypeService;
import project.service.HelpService;

@Controller
public class AdminController {
    private HelpService helpService;
    private CountryService countryService;
    private GameAdditionsService gameAdditionsService;
    private GameTypeService gameTypeService;

    @ModelAttribute("textForm")
    public TextForm getTextForm() {
        return new TextForm();
    }

    @ModelAttribute("gameTypeForm")
    public GameTypeForm getGameTypeForm() {
        return new GameTypeForm();
    }

    @GetMapping("/admin")
    public String admin(){
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

    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
    public String saveTemplate(@ModelAttribute(value = "textForm") TextForm textForm) {
        helpService.saveTextFormToFile(textForm);
        return "redirect:/faq";
    }

    @GetMapping(name = "/admin/setup")
    public String getSetup(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("gameAdditions", gameAdditionsService.findAll());
        model.addAttribute("games", gameTypeService.findAll(pageable));
        return "setup";
    }

    @Autowired
    public void setHelpService(HelpService helpService) {
        this.helpService = helpService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setGameAdditionsService(GameAdditionsService gameAdditionsService) {
        this.gameAdditionsService = gameAdditionsService;
    }

    @Autowired
    public void setGameTypeService(GameTypeService gameTypeService) {
        this.gameTypeService = gameTypeService;
    }
}
