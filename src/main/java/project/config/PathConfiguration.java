package project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.entity.enumtype.FolderName;

import java.io.File;

@Configuration
public class PathConfiguration {

    @Bean(name = "templateDirectory")
    public File getTemplateDirectory() {
        File userFilesDirectory = getUserFilesDirectory();
        File template = new File(userFilesDirectory, String.valueOf(FolderName.TEXT_TEMPLATE));
        if (!template.exists()){
            template.mkdir();
        }
        return template;
    }

    @Bean(name = "homeDirectory")
    public File getHomeDirectory() {
        String property = System.getProperty("catalina.home");
        return new File(property);
    }

    @Bean(name = "userFilesDirectory")
    public File getUserFilesDirectory() {
        File homePath = getHomeDirectory();
        File userFolder = new File(homePath, String.valueOf(FolderName.USER_FILES));
        if (!userFolder.exists()) {
            userFolder.mkdir();
        }
        return userFolder;
    }
}
