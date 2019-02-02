package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.IAdminBusiness;
import org.mql.crowddonating.business.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    IAdminBusiness adminBusiness;
    @Autowired
    IUserServices userServices;

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
    public boolean donatorBan(ModelMap map, @PathVariable long id) {
        adminBusiness.banUser(id);
        return true;
    }
}
