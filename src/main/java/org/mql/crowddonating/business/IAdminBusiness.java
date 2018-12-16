package org.mql.crowddonating.business;

import org.mql.crowddonating.models.Sponsor;

public interface IAdminBusiness extends IUserServices{
	
	/* *** users *** */
	void banUser(long id);
	
	/* *** sponsors *** */
	Sponsor addSponsor(Sponsor sponsor);
	void deleteSponsor(Sponsor sponsor);
	Sponsor updateSponsor(Sponsor sponsor);
	
	/* *** domains *** */
	void deleteDomain(long id);
	
	/* *** case's types *** */
	void deleteType(long id);
	
}
