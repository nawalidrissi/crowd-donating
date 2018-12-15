package org.mql.crowddonating.controllers;

import org.mql.crowddonating.business.implementations.AssociationBusiness;
import org.mql.crowddonating.models.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CaseController {
    @Autowired
    private AssociationBusiness associationBusiness;

    @GetMapping("/cases/add")
    public String addForm(ModelMap map) {
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        map.put("aCase", new Case());
        return "cases/add";
    }

    // @ResponseBody
    @GetMapping("/cases/update/{slug}")
    public String updateForm(ModelMap map, @PathVariable String slug, HttpServletResponse response) {
        map.put("update", true);
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        Case aCase = associationBusiness.getBySlug(slug);
        if (aCase == null) {
            response.setStatus(404);
            return "error/404";
        }
        map.put("aCase", aCase);
        return "cases/add";
    }

    @ResponseBody
    @PutMapping("/cases")
    public String update(Case aCase, @RequestParam MultipartFile imageFile, @RequestParam MultipartFile[] documents) {
        associationBusiness.updateCase(aCase);
        return aCase.toString();
    }

//    @ResponseBody
    @PostMapping("/cases")
    public String add(ModelMap map, Case aCase, @RequestParam MultipartFile imageFile, @RequestParam MultipartFile[] documents) {
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        map.put("aCase", aCase);
        try {
            associationBusiness.addCase(aCase);
        } catch(DataIntegrityViolationException ex){
            errors.put("name", "A case with the same name already exists!");
            return "cases/add";
        }
//        catch (Exception ex) {
//            if (ex.getClass() == org.springframework.dao.DataIntegrityViolationException.class)
//
//        }
        return aCase.toString();
    }

    @InitBinder
    private void DateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }


}
