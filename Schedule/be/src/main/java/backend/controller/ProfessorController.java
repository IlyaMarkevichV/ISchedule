package backend.controller;

import backend.entity.Professor;
import backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private ProfessorService service;

    @Autowired
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Professor saveProfessor(@RequestBody Professor professor) {
        return service.saveProfessor(professor);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProfessor(@PathVariable(name = "id") Integer id) {
        service.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Optional<Professor> getProfessorById(@RequestParam(name = "login") String login) {
        return service.getProfessorByAccountId(login);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Professor> getPage(Pageable pageable) {
        return service.getPage(pageable);
    }
}
