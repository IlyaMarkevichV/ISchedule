package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.ProfessorViewModel;
import com.netcracker.edu.fapi.service.impl.RestPageImpl;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

public interface ProfessorDataService {
    ProfessorViewModel saveProfessor(ProfessorViewModel entityViewModel);

    ProfessorViewModel getProfessorByAccountId(String login);

    void deleteProfessor(Integer id);

    RestPageImpl<ProfessorViewModel> getPage(HttpServletRequest request);
}
