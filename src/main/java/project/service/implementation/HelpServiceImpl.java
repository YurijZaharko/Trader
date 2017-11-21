package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.form.TextForm;
import project.service.HelpService;
import project.service.StringFileReader;
import project.service.StringFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HelpServiceImpl implements HelpService {
    private StringFileWriter stringFileWriter;
    private StringFileReader stringFileReader;
    private File templateDirectory;

    @Override
    public void saveTextFormToFile(TextForm textForm) {
        String templateName = textForm.getTemplateName();
        String mainText = textForm.getMainText();
        stringFileWriter.writeFile(templateName, mainText);
    }

    @Override
    public List<String> getListOfTemplate() {
        List<String> list = new ArrayList<>();
        File[] files = templateDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    list.add(file.getName());
                }
            }
        }
        return list;
    }

    @Override
    public TextForm showFile(String fileName, TextForm textForm) {

        String text = null;
        try {
            String extension = ".txt";
            text = stringFileReader.readFromFile(templateDirectory, fileName + extension);
        } catch (IOException e) {
            e.printStackTrace();
        }

        textForm.setTemplateName(fileName);
        textForm.setMainText(text);
        return textForm;
    }

    @Autowired
    public void setStringFileWriter(StringFileWriter stringFileWriter) {
        this.stringFileWriter = stringFileWriter;
    }

    @Autowired
    public void setStringFileReader(StringFileReader stringFileReader) {
        this.stringFileReader = stringFileReader;
    }

    @Autowired
    public void setTemplateDirectory(File templateDirectory) {
        this.templateDirectory = templateDirectory;
    }
}
