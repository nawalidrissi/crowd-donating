package org.mql.crowddonating.dao;


import org.mql.crowddonating.models.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
	
	
    Page<Case> findByNameLike(String name, Pageable pageable);
    Case findBySlug(String slug);
    
    
}
