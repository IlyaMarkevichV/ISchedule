package backend.controller;

import backend.entity.Student;
import backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**/
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/search")
    public ResponseEntity<Student> getStudentByLogin(@RequestParam(name = "login") String login) {
        Optional<Student> studentOptional = service.getStudentByLogin(login);
        return studentOptional.isPresent() ? ResponseEntity.ok(studentOptional.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Student saveStudentProfessor(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@PathVariable(name = "id") Integer id) {
        service.deleteStudents(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Student> getPage(Pageable pageable) {
        return service.getPage(pageable);
    }
}
