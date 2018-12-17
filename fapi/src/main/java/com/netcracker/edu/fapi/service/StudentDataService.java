package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.StudentViewModel;
import com.netcracker.edu.fapi.models.RestPageImpl;

import javax.servlet.http.HttpServletRequest;

public interface StudentDataService {

    StudentViewModel getStudentByLogin(String login);

    StudentViewModel saveStudent(StudentViewModel viewModel);

    void deleteStudent(Integer id);

    RestPageImpl<StudentViewModel> getPage(HttpServletRequest request);
}
