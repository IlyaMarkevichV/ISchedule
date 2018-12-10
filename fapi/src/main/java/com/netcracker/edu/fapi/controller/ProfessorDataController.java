package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.ProfessorViewModel;
import com.netcracker.edu.fapi.service.ProfessorDataService;
import com.netcracker.edu.fapi.service.impl.RestPageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ba-professors")
public class ProfessorDataController {

    private ProfessorDataService professorDataService;

    @Autowired
    public ProfessorDataController(ProfessorDataService professorDataService) {
        this.professorDataService = professorDataService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProfessorViewModel> saveProfessor(@RequestBody ProfessorViewModel professorViewModel) {
        if (professorViewModel != null) {
            return ResponseEntity.ok(professorDataService.saveProfessor(professorViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProfessorEntity(@PathVariable String id) {
        professorDataService.deleteProfessor(Integer.valueOf(id));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<ProfessorViewModel> getProfessorByAccountId(@RequestParam(name = "login") String login) {
        ProfessorViewModel p =  professorDataService.getProfessorByAccountLogin(login);
        return ResponseEntity.ok(p);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public RestPageImpl<ProfessorViewModel> getPage(HttpServletRequest request) {
       return professorDataService.getPage(request);
    }
}
