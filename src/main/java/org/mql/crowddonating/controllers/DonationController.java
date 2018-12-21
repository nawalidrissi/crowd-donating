package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.IDonorBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.models.BankCard;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.Donation;
import org.mql.crowddonating.models.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DonationController {

	@Autowired
	@Qualifier("publicServicesBusiness")
	private IPublicServices publicServices;

	@Autowired
	@Qualifier("donorBusiness")
	private IDonorBusiness donorBusiness;

	@Autowired
	@Qualifier("userBusiness")
	private IUserServices userServices;

	@GetMapping("/donor/donate/{slug}")
	public String donateForm(Model model, @PathVariable String slug) {

		Case aCase = publicServices.getCaseBySlug(slug);
		Donor donor = userServices.getDonorById(2);
		model.addAttribute("donor", donor);

		Donation donation = new Donation();
		donation.setCase(aCase);

		model.addAttribute("donation", donation);

		return "donor/donate";

	}

	@PostMapping("/donor")
	public String processDonate(Model model, Donation donation) {
		donorBusiness.addDon(donation);
		return "redirect:/cases/" + donation.getCase().getSlug();
	}

}
