package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.StudentProfessor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentProfessorRepository extends CrudRepository<StudentProfessor, Integer> {

    List<StudentProfessor> getStudentProfessorsByAccount_Role(String userRoleName);
}
