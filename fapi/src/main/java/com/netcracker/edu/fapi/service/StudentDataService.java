package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.StudentViewModel;
import com.netcracker.edu.fapi.service.impl.RestPageImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentDataService {

    StudentViewModel saveStudent(StudentViewModel viewModel);

    void deleteStudent(Integer id);

    RestPageImpl<StudentViewModel> getPage(HttpServletRequest request);
}
