package org.mql.crowddonating.business.implementations;

import java.util.List;

import javax.transaction.Transactional;

import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.dao.CaseRepository;
import org.mql.crowddonating.dao.TypeRepository;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PublicServicesBusiness implements IPublicServices {

    @Autowired
    private CaseRepository caseDao;

    @Autowired
    private TypeRepository typeDao;


    public List<Case> getAllCases() {
        return caseDao.findAll();
    }

    public Case getCaseById(long id) {
        return caseDao.findById(id).get();
    }

    public List<Case> getCasesByName(String name) {
        return caseDao.findByNameLike(name);
    }

    @Override
    public Case getCaseBySlug(String slug) {
        return caseDao.findBySlug(slug);
    }

    public List<Type> getAllTypes() {
        return typeDao.findAll();
    }

}
