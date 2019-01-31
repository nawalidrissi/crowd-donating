package org.mql.crowddonating.business.implementations;

import javax.transaction.Transactional;

import org.mql.crowddonating.business.IAdminBusiness;
import org.mql.crowddonating.dao.DomainRepository;
import org.mql.crowddonating.dao.SponsorRepository;
import org.mql.crowddonating.dao.TypeRepository;
import org.mql.crowddonating.dao.UserRepository;

import org.mql.crowddonating.models.Sponsor;
import org.mql.crowddonating.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class  AdminBusiness extends UserBusiness implements IAdminBusiness {

	@Autowired
	private SponsorRepository sponsorDao;
	@Autowired
	private DomainRepository domainDao;
	@Autowired
	private TypeRepository typeDao;
	@Autowired
	private UserRepository userDao;
	
	@Override
	public void banUser(long id) {
		User u = userDao.getOne(id);
		u.setEnabled(true);
		userDao.save(u);

	}

	@Override
	public Sponsor addSponsor(Sponsor sponsor) {
		return sponsorDao.save(sponsor);
	}

	@Override
	public void deleteSponsor(Sponsor sponsor) {
		sponsorDao.delete(sponsor);

	}

	@Override
	public Sponsor updateSponsor(Sponsor sponsor) {
		return sponsorDao.save(sponsor);
	}

	@Override
	public void deleteDomain(long id) {
		domainDao.deleteById(id);

	}

	@Override
	public void deleteType(long id) {
		typeDao.deleteById(id);
	}

}
