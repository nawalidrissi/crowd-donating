package org.mql.crowddonating.business;

import java.util.List;

import org.mql.crowddonating.models.BankCard;
import org.mql.crowddonating.models.Donation;
import org.mql.crowddonating.models.Donor;

public interface IDonorBusiness extends IUserServices{
	
	/* *** bank Cards *** */
	BankCard getCardById(long id);
	BankCard addBankCard(BankCard bankCard);
	void deleteBankCard(long id);
	BankCard updatebankCard(BankCard bankCrd);
	List<BankCard> getAllByDonor(String userName); // cuz spring sec works with username as principal
	
	/* *** donations *** */
	Donation addDonation(Donation donation);
	
	/* *** for signup *** */
	void signup(Donor donor);
	void mailConfirmation(Donor donor);
	Boolean confirmation(String confirmationToken);
}
