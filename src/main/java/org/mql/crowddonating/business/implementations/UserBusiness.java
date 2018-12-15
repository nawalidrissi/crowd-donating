package org.mql.crowddonating.business.implementations;

import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.dao.FileRepository;
import org.mql.crowddonating.dao.UserRepository;
import org.mql.crowddonating.models.Association;
import org.mql.crowddonating.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserBusiness implements IUserServices {
    @Autowired
    private FileRepository fileDao;
    @Autowired
    private UserRepository userDao;

    @Override
    public File saveFile(File file) {
        return fileDao.save(file);
    }

    @Override
    public Association addAssociation(Association association) {
        return userDao.save(association);
    }
}
