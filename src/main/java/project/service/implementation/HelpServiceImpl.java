package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.packageName.FolderName;
import project.form.TextForm;
import project.service.HelpService;
import project.service.StringFileWriter;

@Service
public class HelpServiceImpl implements HelpService{
    private StringFileWriter stringFileWriter;

    @Override
    public void saveTextFormToFile(TextForm textForm) {
        String templateName = textForm.getTemplateName();
        String mainText = textForm.getMainText();

        stringFileWriter.writeFile(templateName, mainText, FolderName.TEXT_TEMPLATE);
    }

    @Autowired
    public void setStringFileWriter(StringFileWriter stringFileWriter) {
        this.stringFileWriter = stringFileWriter;
    }
}
