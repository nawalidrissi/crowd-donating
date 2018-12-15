package org.mql.crowddonating.business;

import java.util.List;

import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Type;


public interface IPublicServices {
	  // Cases
    List<Case> getAllCases();
    Case getCaseById(long id);
    List<Case> getCasesByName(String name);
    Case getCaseBySlug(String slug);
    List<Type> getAllTypes();
   
}
