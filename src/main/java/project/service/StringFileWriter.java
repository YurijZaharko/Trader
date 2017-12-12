package project.service;

import java.io.IOException;

public interface StringFileWriter {
    void writeFile(String filename, String text) throws IOException;

}
