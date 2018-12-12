package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.implementations.AssociationBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaseController {
    @Autowired
    private AssociationBusiness associationBusiness;

    @GetMapping("/cases")
    public String cases() {
        return "cases";
    }

}
