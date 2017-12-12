package project.service;


import project.form.TextForm;

import java.io.IOException;
import java.util.List;

public interface FileUtilitiesService {
    void saveTextFormToFile(TextForm textForm) throws IOException;
    List<String> getListOfTemplate();
    TextForm showFile(String fileName);
}
