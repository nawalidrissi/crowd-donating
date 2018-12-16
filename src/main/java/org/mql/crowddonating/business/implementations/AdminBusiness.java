package org.mql.crowddonating.business.implementations;

import javax.transaction.Transactional;

import org.mql.crowddonating.business.IAdminBusiness;
import org.mql.crowddonating.models.File;
import org.mql.crowddonating.models.Sponsor;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminBusiness extends UserBusiness implements IAdminBusiness {

	@Override
	public File saveFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File deleteFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void banUser(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sponsor addSponsor(Sponsor sponsor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSponsor(Sponsor sponsor) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sponsor updateSponsor(Sponsor sponsor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDomain(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteType(long id) {
		// TODO Auto-generated method stub

	}

}
