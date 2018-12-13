package org.mql.crowddonating.business;

import java.util.List;

import org.mql.crowddonating.models.Case;

public interface IPublicServices {
    List<Case> getAllCases();
    Case getById(long id);
    List<Case> getByName(String name);
}
