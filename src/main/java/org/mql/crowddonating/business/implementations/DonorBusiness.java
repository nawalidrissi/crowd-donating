package org.mql.crowddonating.business.implementations;

import javax.transaction.Transactional;

import org.mql.crowddonating.business.IDonorBusiness;
import org.mql.crowddonating.models.BankCard;
import org.mql.crowddonating.models.Donation;
import org.mql.crowddonating.models.File;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DonorBusiness extends UserBusiness implements IDonorBusiness {

	@Override
	public BankCard addBankCard(BankCard bankCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBankCard(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public BankCard updatebankCard(BankCard bankCrd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDon(Donation donating) {
		// TODO Auto-generated method stub
	}

}
