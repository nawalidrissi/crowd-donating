package org.mql.crowddonating.business;

import org.mql.crowddonating.models.Case;

public interface IAssociationBusiness {
    Case addCase(Case aCase);
    Case updateCase(Case aCase);
    void deleteCase(long id);
}
