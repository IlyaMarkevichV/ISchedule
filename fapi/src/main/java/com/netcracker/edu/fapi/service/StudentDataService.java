package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.StudentViewModel;

import java.util.List;

public interface StudentDataService {
    List<StudentViewModel> getAll();

    StudentViewModel getStudentById(Integer id);

    StudentViewModel saveStudent(StudentViewModel viewModel);

    void deleteStudent(Integer id);
}
