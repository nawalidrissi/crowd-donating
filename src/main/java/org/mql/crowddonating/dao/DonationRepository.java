package org.mql.crowddonating.dao;

import org.mql.crowddonating.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {

}
