package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.form.IndexForm;
import project.service.IndexService;

@Controller
public class IndexController {
    private IndexService indexService;

    @ModelAttribute("findIndexForm")
    public IndexForm getIndexForm(){
        return new IndexForm();
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("listGames", indexService.findAll());
        return "main";
    }

    @GetMapping(value = "/search", params = "search")
    public String getSearchResult(@ModelAttribute("findIndexForm") IndexForm indexForm, Model model){
        model.addAttribute("listGames", indexService.find(indexForm));
        return "main";
    }

    @Autowired
    public void setIndexService(IndexService indexService) {
        this.indexService = indexService;
    }

}
