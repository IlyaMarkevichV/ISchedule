package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SubjectViewModel;

import java.util.List;

public interface SubjectDataService {
    List<SubjectViewModel> getAllSubjects();

    SubjectViewModel getSubjectById(Integer id);

    SubjectViewModel saveSubject(SubjectViewModel subject);

    void deleteSubject(Integer id);
}
