package project.service.implementation;

import org.springframework.stereotype.Service;
import project.entity.packageName.FolderName;
import project.service.StringFileWriter;

import java.io.*;

@Service
public class StringFileWriterImpl implements StringFileWriter {


    @Override
    public void writeFile(String filename, String text, FolderName textTemplate) {
        File homePath = getHomeDirectory();
        String fileExtension = ".txt";
        FileOutputStream fileOutputStream = null;

        File file1 = new File(homePath, String.valueOf(textTemplate));
        if (!file1.exists()){
            file1.mkdir();
        }

        File file = new File(file1, filename + fileExtension);
        byte[] bytes = text.getBytes();
        if (!file.exists()){
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
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File getHomeDirectory(){
        String property = System.getProperty("catalina.home");
        return new File(property);
    }
}
