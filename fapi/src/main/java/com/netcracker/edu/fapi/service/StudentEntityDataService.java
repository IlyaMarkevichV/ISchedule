package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.StudentEntityViewModel;

import java.util.List;

public interface StudentEntityDataService {
    List<StudentEntityViewModel> getAll();

    StudentEntityViewModel getStudentEntityById(Integer id);

    StudentEntityViewModel saveStudentEntity(StudentEntityViewModel entityViewModel);

    void deleteStudentEntity(Integer id);
}
