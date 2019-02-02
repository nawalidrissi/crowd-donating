package org.mql.crowddonating.business;

import org.mql.crowddonating.models.Association;
import org.mql.crowddonating.models.Donor;
import org.mql.crowddonating.models.File;
import org.mql.crowddonating.models.User;
import org.springframework.context.annotation.Primary;

public interface IUserServices {
	
	/* *** files *** */
    File saveFile(File file);
    void deleteFile(long id, String path);
    
    /* *** user *** */
    Donor getDonorById(long id);
    Association getAssociationById(long id);
    Donor getDonorByUsername(String username);
	  User findByEmailIgnoreCase(String email);
}
