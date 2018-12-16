package org.mql.crowddonating.business;

import java.util.List;

import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Domain;
import org.mql.crowddonating.models.Donation;
import org.mql.crowddonating.models.Donor;
import org.mql.crowddonating.models.Event;
import org.mql.crowddonating.models.Type;

public interface IAssociationBusiness extends IUserServices{
	
	/* *** cases *** */
    Case addCase(Case aCase);
    Case updateCase(Case aCase);
    void deleteCase(long id);
    
    /* *** cases types *** */
    Type addType(Type type);
    Type findTypeByLabel(String label);
    
    /* *** dons *** */
    List<Donation> getDonationByCase(long id);
    
    /* *** donors *** */
    List<Donor> getDonorByCase(long id);
    
    /* *** events *** */
    Event addEvent(Event event);
    Event updateEvent(Event event);
    Event deleteEvent(long id);
    
    /* *** association's domains *** */
    Domain addDomain(Domain domain);
    
}
