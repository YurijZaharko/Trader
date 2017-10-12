package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.config.PathConfiguration;
import project.entity.GameType;
import project.form.GameTypeForm;
import project.form.populator.GameTypeFormPopulator;
import project.repository.GameTypeRepository;
import project.service.GameTypeService;

import java.io.File;
import java.io.IOException;

@Service
public class GameTypeServiceImpl implements GameTypeService {
    private GameTypeRepository gameTypeRepository;
    private GameTypeFormPopulator gameTypeFormPopulator;
    private PathConfiguration pathConfiguration;

    @Override
    public Page<GameType> findAll(Pageable pageable) {
        return gameTypeRepository.findAll(pageable);
    }

    @Override
    public void saveGameTypeForm(GameTypeForm gameTypeForm) {
        GameType gameType = gameTypeFormPopulator.convertFormEntity(gameTypeForm);
        Long id = gameTypeForm.getId();
        MultipartFile multipartFile = gameTypeForm.getMultipartFile();

        String fileExtension = saveMultipartFile(multipartFile, id);
        gameType.setImageExtension(fileExtension);
        gameTypeRepository.save(gameType);
    }

    private String saveMultipartFile(MultipartFile multipartFile, Long id) {
        if (!multipartFile.isEmpty()) {
            File imageDirectory = pathConfiguration.getImageDirectory();
            String originalFilename = multipartFile.getOriginalFilename();
            int indexOfDot = originalFilename.lastIndexOf('.');
            String extension = originalFilename.substring(indexOfDot);
            String newFileName = String.valueOf(id) + extension;

            File imageFile = new File(imageDirectory, newFileName);

            try {
                multipartFile.transferTo(imageFile);
                return extension;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Autowired
    public void setPathConfiguration(PathConfiguration pathConfiguration) {
        this.pathConfiguration = pathConfiguration;
    }

    @Autowired
    public void setGameTypeFormPopulator(GameTypeFormPopulator gameTypeFormPopulator) {
        this.gameTypeFormPopulator = gameTypeFormPopulator;
    }

    @Autowired
    public void setGameTypeRepository(GameTypeRepository gameTypeRepository) {
        this.gameTypeRepository = gameTypeRepository;
    }
}
