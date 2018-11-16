package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.StudentProfessor;

import java.util.List;

public interface StudentProfessorService {
    StudentProfessor saveStudentProfessor(StudentProfessor studentProfessor);

    void deleteStudentProfessor(Integer id);

    Iterable<StudentProfessor> getStudentProfessors();

    List<StudentProfessor> getAllStudents();

    List<StudentProfessor> getAllProfessors();
}
