package org.mql.crowddonating.dao;

import org.mql.crowddonating.models.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

}
