package project.form.populator;

import project.entity.GameAdditions;
import project.form.GameAdditionsForm;

public class GameAdditionsFormPopulator {

    public GameAdditionsForm convertEntityForm(GameAdditions gameAdditions) {
        Long id = gameAdditions.getId();
        String gameAdditionsName = gameAdditions.getGameAdditionsName();
        GameAdditionsForm gameAdditionsForm = new GameAdditionsForm();
        gameAdditionsForm.setGameAdditionsId(id);
        gameAdditionsForm.setGameAdditionsName(gameAdditionsName);
        return gameAdditionsForm;
    }

    public GameAdditions convertFormEntity(GameAdditionsForm gameAdditionsForm) {
        GameAdditions gameAdditions = new GameAdditions();
        Long gameAdditionsId = gameAdditionsForm.getGameAdditionsId();
        String gameAdditionsName = gameAdditionsForm.getGameAdditionsName();

        if (gameAdditionsId != null){
            gameAdditions.setId(gameAdditionsId);
        }
        gameAdditions.setGameAdditionsName(gameAdditionsName);
        return gameAdditions;
    }
}