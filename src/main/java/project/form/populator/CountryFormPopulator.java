package project.form.populator;

import project.entity.Country;
import project.form.GameCountryForm;

public class CountryFormPopulator {

    public Country convertFormEntity(GameCountryForm gameCountryForm) {
        Country country = new Country();
        Long id = gameCountryForm.getCountryId();
        String shortName = gameCountryForm.getShortName();
        String fullName = gameCountryForm.getFullName();

        if (id != null){
            country.setId(id);
        }
        country.setShortName(shortName);
        country.setFullName(fullName);
        return country;
    }

    public GameCountryForm convertEntityForm(Country one) {
        GameCountryForm gameCountryForm = new GameCountryForm();
        Long id = one.getId();
        String shortName = one.getShortName();
        String fullName = one.getFullName();

        gameCountryForm.setCountryId(id);
        gameCountryForm.setShortName(shortName);
        gameCountryForm.setFullName(fullName);
        return gameCountryForm;
    }
}
