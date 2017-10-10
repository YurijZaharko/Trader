package project.service;


import project.form.TextForm;

import java.util.List;

public interface HelpService {
    void saveTextFormToFile(TextForm textForm);

    List<String> getListOfTemplate();

    TextForm showFile(String fileName, TextForm textForm);
}
