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
public class UserBusiness extends PublicServicesBusiness implements IUserServices {
    @Autowired
    private FileRepository fileDao;

    @Override
    public File saveFile(File file) {
        return fileDao.save(file);
    }

	@Override
	public File deleteFile() {
		// TODO Auto-generated method stub
		return null;
	}


}
