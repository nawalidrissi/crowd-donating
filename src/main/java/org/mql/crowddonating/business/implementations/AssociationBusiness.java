package org.mql.crowddonating.business.implementations;

import org.mql.crowddonating.business.IAssociationBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.dao.CaseRepository;
import org.mql.crowddonating.dao.TypeRepository;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Type;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class AssociationBusiness extends PublicServicesBusiness implements IAssociationBusiness {
    @Autowired
    private CaseRepository caseDao;

    public Case addCase(Case aCase) {
        aCase.setSlug(Utility.toSlug(aCase.getName()));
        return caseDao.save(aCase);
    }

    public Case updateCase(Case aCase) {
        return caseDao.save(aCase);
    }

	public void deleteCase(long id) {
		caseDao.deleteById(id);
	}


}
