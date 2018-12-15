package org.mql.crowddonating.business;

import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Type;

public interface IAssociationBusiness {
    Case addCase(Case aCase);
    Case updateCase(Case aCase);
    void deleteCase(long id);
    Type addType(Type type);
    Type findTypeByLabel(String label);
}
