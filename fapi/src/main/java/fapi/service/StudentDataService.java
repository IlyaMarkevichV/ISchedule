package fapi.service;

import fapi.models.StudentViewModel;
import fapi.models.RestPageImpl;

import javax.servlet.http.HttpServletRequest;

public interface StudentDataService {

    StudentViewModel getStudentByLogin(String login);

    StudentViewModel saveStudent(StudentViewModel viewModel);

    void deleteStudent(Integer id);

    RestPageImpl<StudentViewModel> getPage(HttpServletRequest request);
}
