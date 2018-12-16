package org.mql.crowddonating.business.implementations;

import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.dao.FileRepository;
import org.mql.crowddonating.dao.UserRepository;
import org.mql.crowddonating.models.Association;
import org.mql.crowddonating.models.File;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class UserBusiness extends PublicServicesBusiness implements IUserServices {
    @Autowired
    private FileRepository fileDao;

    @Override
    public File saveFile(File file) {
        return fileDao.save(file);
    }

    @Override
    public void deleteFile(long id, String repository) {
        File file = fileDao.findById(id).get();
        Path filePath = Paths.get(Utility.uploadDir, repository, file.getPath());
        try {
            Files.delete(filePath);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        fileDao.deleteById(id);
    }
}
