package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.StudentGroup;

import java.util.Optional;

public interface StudentGroupService {
    StudentGroup saveStudent(StudentGroup studentGroup);

    Optional<StudentGroup> getStudentGroupById(Integer id);

    Iterable<StudentGroup> getAllStudentGroups();

    void deleteStudentByAccountId(Integer id);
}
