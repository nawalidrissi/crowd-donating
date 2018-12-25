package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.IDonorBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.models.BankCard;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class CardController {

	@Autowired
	@Qualifier("userBusiness")
	private IUserServices userServices;
	
	@Autowired
	@Qualifier("publicServicesBusiness")
	private IPublicServices publicServices;

	@Autowired
	@Qualifier("donorBusiness")
	private IDonorBusiness donorBusiness;

	@GetMapping("/cards/new")
	public String getCardForm(Model model) {
		model.addAttribute("card", new BankCard());
		return "donor/addCard";
	}
	
	@PostMapping("/cards")
	public String formProcess(Model model, BankCard card, long caseID) {
		card.setDonor(userServices.getDonorById(2));
		donorBusiness.addBankCard(card);
		Case aCase = publicServices.getCaseById(caseID);
		return "redirect:/donor/donate/"+aCase.getSlug();
	}
	
	@GetMapping("/cards/{id}")
	public String updateCardForm(Model model, @PathVariable long id) {
		model.addAttribute("card", donorBusiness.getCardById(id));
		return "donor/updateCard";
	}

	
	@PutMapping("/cards")
	public String formUpdateProcess(Model model, BankCard card) {
		donorBusiness.updatebankCard(card);
		return "redirect:/cases";
	}
}
