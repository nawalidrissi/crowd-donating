package org.mql.crowddonating.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.mql.crowddonating.business.IAdminBusiness;
import org.mql.crowddonating.business.IAssociationBusiness;
import org.mql.crowddonating.business.IPublicServices;
import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.models.Sponsor;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {
    @Autowired
    IAdminBusiness adminBusiness;
    @Autowired
    IUserServices userServices;
    @Autowired
    IPublicServices publicServices;
    @Autowired
    IAssociationBusiness associationBusiness;

    @GetMapping("/admin")
    public String updateCardForm() {
        return "admin/index";
    }

    @GetMapping("/admin/users/donators")
    public String donatorsList(ModelMap map) {
        map.put("donators", adminBusiness.getAllDonators());
        return "admin/users/donators";
    }

    @GetMapping("/admin/users/donators/{username}")
    public String donator(ModelMap map, @PathVariable String username) {
        map.put("donator", userServices.getDonorByUsername(username));
        return "admin/users/donator-details";
    }

    @ResponseBody
    @PatchMapping("/admin/users/donators/{id}/ban")
    public boolean donatorBan(ModelMap map, @PathVariable long id, @RequestParam boolean state) {
        return adminBusiness.banUser(id, state);
    }

    @GetMapping("/admin/users/associations")
    public String associationList(ModelMap map) {
        map.put("associations", publicServices.getAllAssociations());
        return "admin/users/associations";
    }

    @GetMapping("/admin/sponsors/sponsors")
    public String sponsorList(ModelMap map) {
        map.put("sponsors", publicServices.getAllSponsors());
        return "admin/sponsors/sponsors";
    }

    @GetMapping("/admin/sponsors/add")
    public String addForm(ModelMap map) {
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        map.put("sponsor", new Sponsor());

        return "admin/sponsors/add";
    }

    @PostMapping("/admin/sponsors/sponsors")
    public String add(ModelMap map, String name, String url, @RequestParam("logo") MultipartFile logo,
                      String description) {
        /*
         * Map<String, String> errors = new HashMap<>(); map.put("errors", errors);
         * map.put("sponsor", sponsor); try { adminBusiness.addSponsor(sponsor); if
         * (imageFile.isEmpty())05_1961662113953587_4108013232235479040_o.jpg"); else
         * sponsor.setLogo(Utility.upload("images/sponsors/", imageFile));
         * adminBusiness.updateSponsor(sponsor);
         *
         *
         * } catch (DataIntegrityViolationException ex) { errors.put("name",
         * "A sponsor with the same name already exists!"); return "cases/add"; } return
         * "redirect:sponsors/";
         */
        Sponsor sponsor = new Sponsor();
        sponsor.setName(name);
        sponsor.setUrl(url);
        sponsor.setDescription(description);
        sponsor.setLogo(Utility.upload("images/sponsors/", logo));
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        map.put("sponsor", sponsor);
        adminBusiness.addSponsor(sponsor);

        return "redirect:/admin/sponsors/sponsors";
    }

    @ResponseBody
    @PatchMapping("/admin/sponsors/sponsors/{id}/ban")
    public boolean sponsorBan(ModelMap map, @PathVariable long id) {
        adminBusiness.deleteSponsor(new Sponsor(id));
        return true;
    }

    @ResponseBody
    @PatchMapping("/admin/sponsors/sponsors/{id}/update")
    public String updateForm(ModelMap map, @PathVariable Long id, HttpServletResponse response) {
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        Sponsor sponsor = publicServices.getSponsorById(id);
		/*	List<Type> types = publicServices.getAllTypes().stream().filter(type -> !aCase.getTypes().contains(type))
					.collect(Collectors.toList());
			map.put("types", types);*/
        if (sponsor == null) {
            response.setStatus(404);
            return "error/404";
        } else {
            map.put("sponsor", sponsor);
            return "/admin/sponsors/sponsors/{id}/update";
        }
    }


    @PutMapping("/admin/sponsors/sponsors")
    public String update(ModelMap map, Sponsor sponsor, @RequestParam MultipartFile imageFile) {
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        map.put("sponsor", sponsor);

        try {
            if (!imageFile.isEmpty()) {
                sponsor.setLogo(Utility.upload("images/sponsors/", imageFile));
            } else
                sponsor.setLogo(publicServices.getSponsorById(sponsor.getId()).getLogo());
            adminBusiness.updateSponsor(sponsor);

        } catch (DataIntegrityViolationException ex) {
            errors.put("name", "A case with the same name already exists!");
            return "/admin/sponsors/sponsors/{id}/update";
        }

        return "redirect:/admin/sponsors/sponsors";
    }

    @GetMapping("/admin/causes/causes")
    public String causeList(ModelMap map) {
        map.put("causes", adminBusiness.getAllCases());
        return "admin/causes/causes";
    }

    @ResponseBody
    @PatchMapping("/admin/causes/causes/{id}/ban")
    public boolean CaseBan(ModelMap map, @PathVariable long id) {
        associationBusiness.deleteCase(id);
        return true;
    }

    @ResponseBody
    @PatchMapping("/admin/users/associations/{id}/validate")
    public boolean association(ModelMap map, @PathVariable long id) {
        adminBusiness.validateAssociation(id);
        return true;
    }

    @ResponseBody
    @PatchMapping("/admin/users/associations/{id}/ban")
    public boolean associationBan(ModelMap map, @PathVariable long id) {
        adminBusiness.banUser(id);
        return true;
    }
}
