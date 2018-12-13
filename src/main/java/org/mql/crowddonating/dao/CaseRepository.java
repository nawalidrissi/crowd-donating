package org.mql.crowddonating.dao;

import org.mql.crowddonating.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
    
}
