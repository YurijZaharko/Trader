package project.service;


import project.form.TextForm;

import java.util.List;

public interface FileUtilitiesService {
    void saveTextFormToFile(TextForm textForm);
    List<String> getListOfTemplate();
    TextForm showFile(String fileName);
}
