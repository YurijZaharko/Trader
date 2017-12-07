package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.entity.enumtype.FolderName;

import java.io.File;

@Configuration
public class PathConfiguration {
    private File userFilesDirectory;
    private File homeDirectory;

    @Bean(name = "templateDirectory")
    public File getTemplateDirectory() {
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
        File userFolder = new File(homeDirectory, String.valueOf(FolderName.USER_FILES));
        if (!userFolder.exists()) {
            userFolder.mkdir();
        }
        return userFolder;
    }

    @Bean
    public File getImageDirectory(){
        File imageDirectory = new File(homeDirectory, String.valueOf(FolderName.IMAGE));
        if (!imageDirectory.exists()){
            imageDirectory.mkdir();
        }
        return imageDirectory;
    }

    @Autowired
    public void setUserFilesDirectory(File userFilesDirectory) {
        this.userFilesDirectory = userFilesDirectory;
    }

    @Autowired
    public void setHomeDirectory(File homeDirectory) {
        this.homeDirectory = homeDirectory;
    }
}
