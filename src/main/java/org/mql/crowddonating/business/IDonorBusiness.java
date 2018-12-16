package org.mql.crowddonating.business;

import org.mql.crowddonating.models.BankCard;
import org.mql.crowddonating.models.Donation;

public interface IDonorBusiness extends IUserServices{
	
	/* *** bank Cards *** */
	BankCard addBankCard(BankCard bankCard);
	void deleteBankCard(long id);
	BankCard updatebankCard(BankCard bankCrd);
	
	/* *** dons *** */
	void addDon(Donation donating);

}
