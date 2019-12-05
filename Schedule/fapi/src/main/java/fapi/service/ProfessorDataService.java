package fapi.service;

import fapi.models.ProfessorViewModel;
import fapi.models.RestPageImpl;

import javax.servlet.http.HttpServletRequest;

public interface ProfessorDataService {
    ProfessorViewModel saveProfessor(ProfessorViewModel entityViewModel);

    ProfessorViewModel getProfessorByAccountLogin(String login);

    void deleteProfessor(Integer id);

    RestPageImpl<ProfessorViewModel> getPage(HttpServletRequest request);
}
