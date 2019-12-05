package fapi.controller;

import fapi.models.StudentViewModel;
import fapi.service.StudentDataService;
import fapi.models.RestPageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ba-students")
public class StudentDataController {

    private StudentDataService studentDataService;

    @Autowired
    public StudentDataController(StudentDataService studentDataService) {
        this.studentDataService = studentDataService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<StudentViewModel> saveStudentEntity(@RequestBody StudentViewModel studentViewModel) {
        if (studentViewModel != null) {
            return ResponseEntity.ok(studentDataService.saveStudent(studentViewModel));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentEntity(@PathVariable String id) {
        studentDataService.deleteStudent(Integer.valueOf(id));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public RestPageImpl<StudentViewModel> getPage(HttpServletRequest request) {
        return studentDataService.getPage(request);
    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<StudentViewModel> getProfessorByAccountId(@RequestParam(name = "login") String login) {
        return ResponseEntity.ok(studentDataService.getStudentByLogin(login));

    }
}
