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
import project.form.TestForm;
import project.service.CountryService;
import project.service.GameAdditionsService;
import project.service.GameTypeService;

import java.util.List;

@Controller
public class GameSetupController {
    private CountryService countryService;
    private GameAdditionsService gameAdditionsService;
    private GameTypeService gameTypeService;
    private static final String SETUP = "setup";

    @ModelAttribute("gameTypeForm")
    public GameTypeForm getGameTypeForm() {
        return new GameTypeForm();
    }

    @ModelAttribute("gameAdditionsForm")
    public GameAdditionsForm getGameAdditionsForm() {
        return new GameAdditionsForm();
    }

    @ModelAttribute("testAdd")
    public List<GameAdditions> addTest(){
        return gameAdditionsService.findAll();
    }

    @InitBinder("gameTypeForm")
    protected void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(GameAdditions.class, new GameAdditionsEditor(gameAdditionsService));
    }

    @InitBinder("gameAdditionsForm")
    protected void initBinderSecond(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(GameAdditions.class, new GameAdditionsEditor(gameAdditionsService));
    }

    @InitBinder("testForm")
    protected void initBinderTestForm(WebDataBinder webDataBinder){
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
    @PostMapping(value = "admin/gameAdditions/save")
    public String saveAdditions(@ModelAttribute("gameAdditionsForm") GameAdditionsForm gameAdditionsForm){
        gameAdditionsService.save(gameAdditionsForm);
        return SETUP;
    }

    @RequestMapping(value = "/admin/testJsp", method = RequestMethod.GET)
    public String getTest(Model model){
        model.addAttribute("testForm", new TestForm());
        return "testJsp";
    }

    @RequestMapping(value = "/admin/testJsp", method = RequestMethod.POST)
    public String test(@ModelAttribute("testForm") TestForm testForm){
        List<GameAdditions> gameAdditionsSet = testForm.getGameAdditionsSet();
        System.out.println(gameAdditionsSet);
        return "testJsp";
    }

    /**
     * Populate model for getSetup, editGame methods
     **/
    private void addModel(Model model, Pageable pageable) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("gameAdditionsList", gameAdditionsService.findAll());
        model.addAttribute("gameAdditions", gameAdditionsService.findAll());
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
