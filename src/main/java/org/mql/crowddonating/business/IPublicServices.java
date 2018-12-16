package org.mql.crowddonating.business;

import java.util.List;

import org.mql.crowddonating.models.Association;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Domain;
import org.mql.crowddonating.models.Event;
import org.mql.crowddonating.models.Sponsor;
import org.mql.crowddonating.models.Type;
import org.mql.crowddonating.models.User;


public interface IPublicServices {
	
	/* *** Cases *** */
    List<Case> getAllCases();
    Case getCaseById(long id);
    List<Case> getCasesByName(String name);
    Case getCaseBySlug(String slug);
    List<Type> getAllTypes();
    
    /* *** user *** */
    @Deprecated
    Association addAssociation(Association association);
    User login(String username, String password);
    
    /* *** associations *** */
    List<Association> getAllAssociations();
    List<Association> getAssociationsByDomain();
    List<Association> getAssociationsByName();
    Association getAssociationById(long id);    
    
    /* *** events *** */
    List<Event> getAllEvents();
    Case getEventById(long id);
    List<Case> getEventByName(String name);
    
    /* *** sponsors *** */
    List<Sponsor> getAllSponsors();
    Case getSponsorById(long id);
    List<Case> getSponsorByName(String name);
    
    /* *** domains *** */
    List<Domain> getAllDomains();
    
    
}
