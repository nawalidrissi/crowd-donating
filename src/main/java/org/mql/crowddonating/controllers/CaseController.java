package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.IAssociationBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.business.implementations.UserBusiness;
import org.mql.crowddonating.models.Association;
import org.mql.crowddonating.models.Case;
import org.mql.crowddonating.models.File;
import org.mql.crowddonating.models.Type;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CaseController {

	@Autowired
	@Qualifier("userBusiness")
	private IUserServices userBusiness;

	@Autowired
	@Qualifier("associationBusiness")
	private IAssociationBusiness associationBusiness;

	@Autowired
	@Qualifier("publicServicesBusiness")
	private IPublicServices publicServices;

	@GetMapping("/cases")
	public String cases(Model model) {
		model.addAttribute("types", publicServices.getAllTypes());
		model.addAttribute("cases", publicServices.getAllCases());
		return "cases/cases";
	}

	@GetMapping("/cases/search/{name}")
	public String cases(Model model, @PathVariable String name) {
		model.addAttribute("types", publicServices.getAllTypes());
		model.addAttribute("name", name);
		model.addAttribute("cases", publicServices.getCasesByName("%" + name + "%"));

		return "cases/cases";
	}

	@GetMapping("/cases/{slug}")
	public String caseBySlug(ModelMap map, @PathVariable String slug, HttpServletResponse response) {
		Case aCase = publicServices.getCaseBySlug(slug);
		if (aCase == null) {
			response.setStatus(404);
			return "error/404";
		}
		map.put("case", aCase);
		return "cases/details";
	}

	@DeleteMapping("/cases/{id}")
	public String delete(@PathVariable long id, Model model) {
		associationBusiness.deleteCase(id);
		return "cases/cases";
	}

	@GetMapping("/cases/add")
	public String addForm(ModelMap map) {
		Map<String, String> errors = new HashMap<>();
		map.put("errors", errors);
		map.put("aCase", new Case());
		map.put("types", publicServices.getAllTypes());
		return "cases/add";
	}

	// @ResponseBody
	@GetMapping("/cases/update/{slug}")
	public String updateForm(ModelMap map, @PathVariable String slug, HttpServletResponse response) {
		map.put("update", true);
		Map<String, String> errors = new HashMap<>();
		map.put("errors", errors);
		Case aCase = publicServices.getCaseBySlug(slug);
		if (aCase == null) {
			response.setStatus(404);
			return "error/404";
		}
		map.put("aCase", aCase);
		return "cases/update";
	}

	@PutMapping("/cases")
	public String update(ModelMap map, Case aCase, @RequestParam MultipartFile imageFile,
			@RequestParam MultipartFile[] documents) {
		Map<String, String> errors = new HashMap<>();
		map.put("errors", errors);
		map.put("aCase", aCase);
		try {
			associationBusiness.updateCase(aCase);
		} catch (DataIntegrityViolationException ex) {
			errors.put("name", "A case with the same name already exists!");
			return "cases/add";
		}
		return "redirect:cases/" + aCase.getSlug();
	}

	@PostMapping("/cases")
	public String add(ModelMap map, Case aCase, String[] caseTypes, @RequestParam MultipartFile imageFile,
			@RequestParam MultipartFile[] documents) {
		for (String ct : caseTypes) {
			Type type = new Type();
			type.setLabel(ct);
			try {
				associationBusiness.addType(type);
			} catch (Exception ex) {
				type = associationBusiness.findTypeByLabel(ct);
			}
			aCase.addType(type);
		}
		Map<String, String> errors = new HashMap<>();
		map.put("errors", errors);
		map.put("aCase", aCase);
		try {
			associationBusiness.addCase(aCase);
			aCase.setImage(Utility.upload("images/cases/", imageFile));
			associationBusiness.updateCase(aCase);

			for (MultipartFile doc : documents) {
				File file = new File();
				file.setPath(Utility.upload("files/cases/", doc));
				file.setType("document");
				file.setCase(aCase);
				userBusiness.saveFile(file);
			}

		} catch (DataIntegrityViolationException ex) {
			errors.put("name", "A case with the same name already exists!");
			return "cases/add";
		}
		return "redirect:cases/" + aCase.getSlug();
	}

	@InitBinder
	private void DateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@PostConstruct
	public void createAssoc() {
		Association assoc = new Association();
		assoc.setId(1);
		assoc.setEmail("assoc-123@gmail.com");
		assoc.setUsername("assoc-1");
		assoc.setName("assoc 1");
		assoc.setCover("cover.jpg");
		assoc.setBanned(false);
		assoc.setAvatar("bd7dec8a-a7ab-4cfb-b1bc-2114a8662c55_akali.png");

		publicServices.addAssociation(assoc);

		System.out.println("Assoc added.");
	}

}
