package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.service.StringFileWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StringFileWriterImpl implements StringFileWriter {

    private File templateDirectory;

    @Override
    public void writeFile(String filename, String text) {
        String fileExtension = ".txt";
        FileOutputStream fileOutputStream = null;

        File file = new File(templateDirectory, filename + fileExtension);
        byte[] bytes = text.getBytes();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Autowired
    public void setTemplateDirectory(File templateDirectory) {
        this.templateDirectory = templateDirectory;
    }
}
