package project.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.form.TextForm;
import project.service.FileUtilitiesService;
import project.service.StringFileReader;
import project.service.StringFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUtilitiesServiceImpl implements FileUtilitiesService {
    private StringFileWriter stringFileWriter;
    private StringFileReader stringFileReader;
    private File templateDirectory;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtilitiesServiceImpl.class);

    @Override
    public void saveTextFormToFile(TextForm textForm) throws IOException {
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
    public TextForm showFile(String fileName) {
        String text;
        try {
            text = readFromFile(fileName);
        } catch (IOException e) {
            text = "";
            LOGGER.error("Could not read " + fileName, e);
        }

        TextForm textForm = new TextForm();
        textForm.setTemplateName(fileName);
        textForm.setMainText(text);
        return textForm;
    }

    private String readFromFile(String fileName) throws IOException {
        String fileNameWithExtension = fileName + ".txt";
        return stringFileReader.readFromFile(templateDirectory, fileNameWithExtension);
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
