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
    public void writeFile(String filename, String text) throws IOException {
        String fileExtension = ".txt";
        FileOutputStream fileOutputStream = null;

        File file = new File(templateDirectory, filename + fileExtension);
        byte[] bytes = text.getBytes();
        boolean newFile = false;
        if (!file.exists()) {
            newFile = file.createNewFile();

        }
        if (newFile) {
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }

    }

    @Autowired
    public void setTemplateDirectory(File templateDirectory) {
        this.templateDirectory = templateDirectory;
    }
}
