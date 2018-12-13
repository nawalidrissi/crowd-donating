package org.mql.crowddonating.dao;

import java.util.List;

import org.mql.crowddonating.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CaseRepository extends JpaRepository<Case, Long> {

	@Query("SELECT c FROM Case c WHERE c.label like name")
	List<Case> findByName(@Param("name") String name);
    
}
