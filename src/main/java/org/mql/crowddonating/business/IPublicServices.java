package org.mql.crowddonating.business;

import org.mql.crowddonating.models.Case;
import org.springframework.data.domain.Page;

public interface IPublicServices {
    Page<Case> getAll();
    Case getById(long id);
    Case getBySlug(String slug);
    Page<Case> getByName(String name);
}
