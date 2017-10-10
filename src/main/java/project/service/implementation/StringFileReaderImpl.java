package project.service.implementation;

import org.springframework.stereotype.Service;
import project.service.StringFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

@Service
public class StringFileReaderImpl implements StringFileReader {
    @Override
    public String readFromFile(File path, String filename) throws IOException {
        File file = new File(path, filename);
        byte [] bytes = Files.readAllBytes(file.toPath());
        return new String(bytes, Charset.defaultCharset());
    }
}
