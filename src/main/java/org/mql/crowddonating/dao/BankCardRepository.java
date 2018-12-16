package org.mql.crowddonating.dao;

import org.mql.crowddonating.models.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {

}
