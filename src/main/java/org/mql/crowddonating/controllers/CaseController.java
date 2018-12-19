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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    public String cases(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "8") int size) {
        if (page <= 0)
            page = 1;
        Page<Case> cases = publicServices.getAllCases(page - 1, size);
        return getPageCases(model, page, cases);
    }

    private String getPageCases(Model model, @RequestParam(name = "page", defaultValue = "1") int page, Page<Case> cases) {
        int[] pages = new int[cases.getTotalPages()];
        model.addAttribute("types", publicServices.getAllTypes());
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("cases", cases);
        return "cases/cases";
    }

    @GetMapping("/cases/search")
    public String cases(Model model, @RequestParam(name = "name") String name,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "8") int size) {
        Page<Case> cases = publicServices.getCasesByName("%" + name + "%", page - 1, size);
        return getPageCases(model, page, cases);
    }

    @GetMapping("/cases/type/{id}")
    public String casesByType(Model model, @PathVariable long id) {
        Type type = publicServices.getTypeById(id);
        model.addAttribute("types", publicServices.getAllTypes());
        model.addAttribute("cases", type.getCases());
        model.addAttribute("currentPage", 1);
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
        Case aCase = publicServices.getCaseById(id);
        System.out.println("Case : " + aCase.getName());
        for (File file : aCase.getFiles()) {
            userBusiness.deleteFile(file.getId(), "files/cases/");
        }
        Path filePath = Paths.get(Utility.uploadDir, "images/cases/", aCase.getImage());
        try {
            Files.delete(filePath);
        } catch (IOException ignored) {
        }
        System.out.println();
        associationBusiness.deleteCase(id);
        return "redirect:/cases";
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
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        Case aCase = publicServices.getCaseBySlug(slug);
        List<Type> types = publicServices.getAllTypes().stream().filter(type -> !aCase.getTypes().contains(type))
                .collect(Collectors.toList());
        map.put("types", types);
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
            if (!imageFile.isEmpty()) {
                aCase.setImage(Utility.upload("images/cases/", imageFile));
            } else
                aCase.setImage(publicServices.getCaseBySlug(aCase.getSlug()).getImage());
            associationBusiness.updateCase(aCase);
            uploadDocuments(aCase, documents);
            associationBusiness.updateCase(aCase);
        } catch (DataIntegrityViolationException ex) {
            errors.put("name", "A case with the same name already exists!");
            return "cases/update";
        }
        return "redirect:cases/" + aCase.getSlug();
    }

    @PostMapping("/cases")
    public String add(ModelMap map, Case aCase, String[] caseTypes, @RequestParam MultipartFile imageFile,
                      @RequestParam MultipartFile[] documents) {
        Map<String, String> errors = new HashMap<>();
        map.put("errors", errors);
        map.put("aCase", aCase);
        map.put("types", publicServices.getAllTypes());
        if (caseTypes == null) {
            errors.put("types", "At least one type is required!");
            return "cases/add";
        } else if (caseTypes.length == 0) {
            errors.put("types", "At least one type is required!");
            return "cases/add";
        } else {
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
        }
        try {
            associationBusiness.addCase(aCase);
            if (imageFile.isEmpty())
                aCase.setImage("blog-1.jpg");
            else
                aCase.setImage(Utility.upload("images/cases/", imageFile));
            associationBusiness.updateCase(aCase);
            uploadDocuments(aCase, documents);
        } catch (DataIntegrityViolationException ex) {
            errors.put("name", "A case with the same name already exists!");
            return "cases/add";
        }
        return "redirect:cases/" + aCase.getSlug();
    }

    @DeleteMapping("/cases/files/{id}")
    public String deleteFile(@PathVariable long id, HttpServletRequest request) {
        userBusiness.deleteFile(id, "/files/cases/");
        return "redirect:" + request.getHeader("Referer");
    }

    private void uploadDocuments(Case aCase, MultipartFile[] documents) {
        for (MultipartFile doc : documents) {
            if (!"".equals(doc.getName())) {
                File file = new File();
                file.setPath(Utility.upload("files/cases/", doc));
                file.setType("document");
                file.setCase(aCase);
                userBusiness.saveFile(file);
            }
        }
    }

//	@InitBinder
//	private void DateBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
//		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//		binder.registerCustomEditor(Date.class, editor);
//	}

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
