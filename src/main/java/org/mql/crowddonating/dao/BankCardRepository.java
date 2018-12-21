package org.mql.crowddonating.dao;

import java.util.List;

import org.mql.crowddonating.models.BankCard;
import org.mql.crowddonating.models.Donor;
import org.mql.crowddonating.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {

	List<BankCard> findByDonor(User donor);

}
