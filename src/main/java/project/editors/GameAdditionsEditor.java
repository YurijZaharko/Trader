package project.editors;

import project.entity.GameAdditions;
import project.service.GameAdditionsService;

import java.beans.PropertyEditorSupport;

public class GameAdditionsEditor extends PropertyEditorSupport {
    private final GameAdditionsService gameAdditionsService;

    public GameAdditionsEditor(GameAdditionsService gameAdditionsService) {
        this.gameAdditionsService = gameAdditionsService;
    }

    @Override
    public void setAsText(String text) {
        Long id = Long.valueOf(text);
        GameAdditions one = gameAdditionsService.findOne(id);
        setValue(one);
    }

    @Override
    public String getAsText() {
        GameAdditions gameAdditions  = (GameAdditions) this.getValue();
        return gameAdditions.getGameAdditionsName();
    }

}
