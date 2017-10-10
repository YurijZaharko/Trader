package project.service;

import java.io.File;
import java.io.IOException;

public interface StringFileReader {
    String readFromFile(File path, String filename) throws IOException;
}
