package org.mql.crowddonating.dao;


import java.util.List;

import org.mql.crowddonating.models.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CaseRepository extends JpaRepository<Case, Long> {
    Page<Case> findByNameLike(String name, Pageable pageable);
    Case findBySlug(String slug);    
	List<Case> findFirst3ByOrderByIdDesc();
}
