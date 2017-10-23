package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.editors.GameAdditionsEditor;
import project.entity.GameAdditions;
import project.form.GameAdditionsForm;
import project.form.GameTypeForm;
import project.form.GameCountryForm;
import project.service.CountryService;
import project.service.GameAdditionsService;
import project.service.GameTypeService;

@Controller
public class GameSetupController {
    private CountryService countryService;
    private GameAdditionsService gameAdditionsService;
    private GameTypeService gameTypeService;
    private static final String SETUP = "setup";
    private static final String SETUP_ADDITIONS = "setupAdditions";
    private static final String COUNTRIES = "countries";

    @ModelAttribute("gameTypeForm")
    public GameTypeForm getGameTypeForm() {
        return new GameTypeForm();
    }

    @ModelAttribute("gameAdditionsForm")
    public GameAdditionsForm getGameAdditionsForm() {
        return new GameAdditionsForm();
    }

    @ModelAttribute("gameCountryForm")
    public GameCountryForm getGameCountryForm(){
        return new GameCountryForm();
    }

    @InitBinder("gameTypeForm")
    protected void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(GameAdditions.class, new GameAdditionsEditor(gameAdditionsService));
    }

    @InitBinder("gameAdditionsForm")
    protected void initBinderSecond(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(GameAdditions.class, new GameAdditionsEditor(gameAdditionsService));
    }

    @GetMapping(value = "/admin/setup")
    public String getSetup(@PageableDefault Pageable pageable, Model model) {
        addModel(model, pageable);
        return SETUP;
    }

    @RequestMapping(value = "/admin/setup/saveGame", method = RequestMethod.POST)
    public String saveGame(@ModelAttribute("gameTypeForm") GameTypeForm gameTypeForm) {
        gameTypeService.saveGameTypeForm(gameTypeForm);
        return "redirect:/admin/setup";
    }

    @GetMapping("admin/game/edit/{id}")
    public String editGame(@PathVariable("id") Long id, Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("gameTypeForm", gameTypeService.findForForm(id));
        addModel(model, pageable);
        return SETUP;
    }

    @GetMapping(value = "/admin/game/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id) {
        gameTypeService.deleteById(id);
        return "redirect:/admin/setup";
    }

    @GetMapping(value = "/admin/setupAdditions")
    public String getAdditions(Model model,@PageableDefault Pageable pageable){
        model.addAttribute("additions", gameAdditionsService.findAll(pageable));
        return SETUP_ADDITIONS;
    }

    @PostMapping(value = "admin/gameAdditions/save")
    public String saveAdditions(@ModelAttribute("gameAdditionsForm") GameAdditionsForm gameAdditionsForm){
        gameAdditionsService.save(gameAdditionsForm);
        return "redirect:/admin/setupAdditions";
    }

    @GetMapping(value = "/admin/addition/edit/{id}")
    public String editAdditions(@PathVariable("id") Long id, Model model, @PageableDefault Pageable pageable){
        model.addAttribute("gameAdditionsForm", gameAdditionsService.findForGameAdditionsForm(id));
        model.addAttribute("additions", gameAdditionsService.findAll(pageable));
        return SETUP_ADDITIONS;
    }

    @GetMapping(value = "/admin/addition/delete/{id}")
    public String deleteAdditions(@PathVariable("id") Long id){
        gameAdditionsService.deleteById(id);
        return "redirect:/admin/setupAdditions";
    }

    @GetMapping(value = "/admin/setupCountries")
    public String getCountry(Model model, @PageableDefault Pageable pageable){
        model.addAttribute(COUNTRIES, countryService.findAll(pageable));
        return "setupCountries";
    }

    @PostMapping(value = "/admin/country/save")
    public String saveCountry(@ModelAttribute(value = "gameCountryForm") GameCountryForm gameCountryForm){
        countryService.saveGameCountryForm(gameCountryForm);
        return "redirect:/admin/setupCountries";
    }
    @GetMapping(value = "/admin/country/edit/{id}")
    public String editCountry(@PathVariable("id") Long id, Model model, @PageableDefault Pageable pageable){
        model.addAttribute("gameCountryForm", countryService.findForGameCountryForm(id));
        model.addAttribute(COUNTRIES, countryService.findAll(pageable));
        return "setupCountries";
    }
    @GetMapping(value = "/admin/country/delete/{id}")
    public String deleteCountry(@PathVariable("id") Long id){
        countryService.deleteById(id);
        return "redirect:/admin/setupCountries";
    }

    /**
     * Populate model for getSetup
     **/
    private void addModel(Model model, Pageable pageable) {
        model.addAttribute(COUNTRIES, countryService.findAll());
        model.addAttribute("gameAdditionsList", gameAdditionsService.findAll());
        model.addAttribute("games", gameTypeService.findAll(pageable));
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
