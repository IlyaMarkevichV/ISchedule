package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.ProfessorViewModel;
import com.netcracker.edu.fapi.models.RestPageImpl;

import javax.servlet.http.HttpServletRequest;

public interface ProfessorDataService {
    ProfessorViewModel saveProfessor(ProfessorViewModel entityViewModel);

    ProfessorViewModel getProfessorByAccountLogin(String login);

    void deleteProfessor(Integer id);

    RestPageImpl<ProfessorViewModel> getPage(HttpServletRequest request);
}
