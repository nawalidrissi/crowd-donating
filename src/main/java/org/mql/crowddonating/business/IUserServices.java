package org.mql.crowddonating.business;

import org.mql.crowddonating.models.File;
import org.mql.crowddonating.models.User;

public interface IUserServices {
	
	/* *** files *** */
    File saveFile(File file);
    File deleteFile();
    
}
