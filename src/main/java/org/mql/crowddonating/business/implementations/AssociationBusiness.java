package org.mql.crowddonating.business.implementations;

import org.mql.crowddonating.business.IAssociationBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.dao.CaseRepository;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AssociationBusiness implements IPublicServices, IAssociationBusiness {
    @Autowired
    private CaseRepository caseDao;

    public Case addCase(Case aCase) {
        return caseDao.save(aCase);
    }

    public Case updateCase(Case aCase) {
        return null;
    }

    public void deleteCase(long id) {

    }

    public Page<Case> getAll() {
        return null;
    }

    public Case getById(long id) {
        return null;
    }

    public Page<Case> getByName(String name) {
        return null;
    }
}
