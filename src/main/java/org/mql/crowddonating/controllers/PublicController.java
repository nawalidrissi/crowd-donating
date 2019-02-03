package org.mql.crowddonating.controllers;

import java.util.List;
import java.util.Map;

import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublicController {

	@Autowired
	@Qualifier("publicServicesBusiness")
	public IPublicServices publicServices;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("events", publicServices.getLastNEvents());
		model.addAttribute("cases", publicServices.findLastNCases());
		model.addAttribute("sponsors", publicServices.getAllSponsors());
		model.addAttribute("stats", publicServices.globalStats());
		
		return "public/index";
	}
}
