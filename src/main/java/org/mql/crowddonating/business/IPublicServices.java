package org.mql.crowddonating.business;

import java.util.List;

import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Type;


public interface IPublicServices {
	
    List<Case> getAllCases();
    Case getCaseById(long id);
    List<Case> getCasesByName(String name);
    
    List<Type> getAllTypes();
    
    
}
