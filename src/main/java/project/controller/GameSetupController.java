package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.form.GameAdditionsForm;
import project.form.GameTypeForm;
import project.service.CountryService;
import project.service.GameAdditionsService;
import project.service.GameTypeService;

@Controller
public class GameSetupController {
    private CountryService countryService;
    private GameAdditionsService gameAdditionsService;
    private GameTypeService gameTypeService;

    @ModelAttribute("gameTypeForm")
    public GameTypeForm getGameTypeForm() {
        return new GameTypeForm();
    }

    @ModelAttribute("gameAdditionsForm")
    public GameAdditionsForm getGameAdditionsForm(){return new GameAdditionsForm();}

    @GetMapping(value = "/admin/setup")
    public String getSetup(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("gameAdditionsList", gameAdditionsService.findAll());
        model.addAttribute("gameAdditions", gameAdditionsService.findAll(pageable));
        model.addAttribute("games", gameTypeService.findAll(pageable));
        return "setup";
    }

    @RequestMapping(value = "/admin/setup", method = RequestMethod.POST)
    public String saveGame(@ModelAttribute(name = "gameTypeForm") GameTypeForm gameTypeForm){
        gameTypeService.saveGameTypeForm(gameTypeForm);
        return "redirect:/admin/setup";
    }

    @GetMapping("admin/game/edit/{id}")
    public String editGame(@PathVariable("id") Long id, Model model){
        model.addAttribute("gameAdditionsForm", gameAdditionsService.findForForm(id));
        return "setup";
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
