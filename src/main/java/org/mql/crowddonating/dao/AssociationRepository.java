package org.mql.crowddonating.dao;

import org.mql.crowddonating.models.Association;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociationRepository extends JpaRepository<Association, Long> {
	


}
