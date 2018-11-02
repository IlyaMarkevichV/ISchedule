package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Subject saveSubject(Subject subject);

    Optional<Subject> getSubjectById(Integer id);

    Iterable<Subject> getAllSubjects();

    void deleteSubject(Integer id);
}
