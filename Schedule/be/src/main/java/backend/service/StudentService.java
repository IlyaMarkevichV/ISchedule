package backend.service;

import backend.entity.Student;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student entity);

    Optional<Student> getStudentByLogin(String login);

    void deleteStudents(Integer id);

    Page<Student> getPage(Pageable pageable);
}
