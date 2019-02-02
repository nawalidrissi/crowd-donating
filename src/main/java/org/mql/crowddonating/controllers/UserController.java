package org.mql.crowddonating.controllers;


import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.mql.crowddonating.business.IDonorBusiness;
import org.mql.crowddonating.business.IUserServices;
import org.mql.crowddonating.models.*;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {
    @Autowired
    @Qualifier("userBusiness")
    private IUserServices userBusiness;
    @Autowired
    @Qualifier("donorBusiness")
    private IDonorBusiness donorBusiness;


    @GetMapping("/association/profile/{id}")
    public String associationById(ModelMap map, @PathVariable long id, HttpServletResponse response) {
        Association assoc = userBusiness.getAssociationById(id);
       /* if (assoc == null) {
            response.setStatus(404);
            return "error/404";
        }*/
        map.put("association",assoc );
        return "association/profile";
    }

    @GetMapping("/association/{idA}/file/delete/{id}")
    public String deleteFile(@PathVariable long idA,@PathVariable long id,Model model) {


        userBusiness.deleteFile(id, "files/associations/");

         return "redirect:/association/profile/"+idA;
    }


    @GetMapping("/donor/profile/{id}")
    public String donorById(ModelMap map, @PathVariable long id, HttpServletResponse response) {
        Donor donor = userBusiness.getDonorById(id);
        if (donor == null) {
            response.setStatus(404);
            return "error/404";
        }
        map.put("donor",donor );
        return "donor/profile";
    }


    @GetMapping("/donor/{idD}/card/delete/{id}")
    public String deleteCard(@PathVariable long idD,@PathVariable long id, Model model) {
        System.out.println(id+"ddddddddddddddddd");
    donorBusiness.deleteBankCard(id);

        return "redirect:/donor/profile/"+idD;
    }


}
