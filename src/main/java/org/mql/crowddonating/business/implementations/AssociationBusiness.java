package org.mql.crowddonating.business.implementations;

import org.mql.crowddonating.business.IAssociationBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.dao.CaseRepository;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return caseDao.save(aCase);
    }

    public void deleteCase(long id) {
    	caseDao.deleteById(id);
    }

    public List<Case> getAllCases() {
        return caseDao.findAll();
    }

    public Case getById(long id) {
        return caseDao.findById(id).get();
    }

    public List<Case> getByName(String name) {
        return caseDao.findByName(name);
    }
}
