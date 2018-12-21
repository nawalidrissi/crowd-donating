package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.IDonorBusiness;
import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.models.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CardController {

	@Autowired
	@Qualifier("userBusiness")
	private IUserServices userServices;

	@Autowired
	@Qualifier("donorBusiness")
	private IDonorBusiness donorBusiness;

	@GetMapping("/cards/new")
	public String getCardForm(Model model) {
		model.addAttribute("card", new BankCard());
		return "donor/addCard";
	}

	@PostMapping("/cards")
	public String formProcess(Model model, BankCard card) {
		card.setDonor(userServices.getDonorById(2));
		donorBusiness.addBankCard(card);
		return "redirect:/cases";
	}

}
