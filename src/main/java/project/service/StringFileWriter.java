package project.service;

import project.entity.packageName.FolderName;

public interface StringFileWriter {
    void writeFile(String filename, String text, FolderName textTemplate);
}
