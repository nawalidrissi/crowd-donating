package org.mql.crowddonating.dao;

import java.util.List;

import org.mql.crowddonating.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
    List<Case> findByNameLike(String name);
    Case findBySlug(String slug);
}
