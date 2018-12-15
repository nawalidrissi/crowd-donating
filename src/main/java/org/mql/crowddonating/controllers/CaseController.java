package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.IAssociationBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CaseController {

	@Autowired
	@Qualifier("associationBusiness")
	private IAssociationBusiness associationBusiness;

	@Autowired
	@Qualifier("publicServicesBusiness")
	private IPublicServices publicServices;

	@GetMapping("/cases")
	public String cases(Model model, String... name) {
		model.addAttribute("types", publicServices.getAllTypes());
		if (name == null || name.length == 0)
			model.addAttribute("cases", publicServices.getAllCases());
		else {
			model.addAttribute("name", name[0]);
			model.addAttribute("cases", publicServices.getCasesByName("%" + name[0] + "%"));
		}
		return "cases/cases";
	}

	@GetMapping("/cases/add")
	public String add(Model model) {
		model.addAttribute("aCase", new Case());
		return "cases/addcase";
	}

	@GetMapping("/cases/{id}")
	public String showCase(@PathVariable long id, Model model) {
		model.addAttribute("case", publicServices.getCaseById(id));
		return "cases/details";
	}

	@DeleteMapping("/cases/{id}")
	public String delete(@PathVariable long id, Model model) {
		associationBusiness.deleteCase(id);
		return "cases/cases";
	}

	@PostMapping("/cases")
	public String processAdd(Model model, Case aCase) {
		System.out.println(aCase);
		aCase = associationBusiness.addCase(aCase);
		model.addAttribute("case", aCase);
		return "redirect:cases/" + aCase.getId();
	}

}
