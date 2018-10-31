package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.ProfessorViewModel;

import java.util.List;

public interface ProfessorDataService {
    List<ProfessorViewModel> getAll();

    ProfessorViewModel getProfessorById(Integer id);

    ProfessorViewModel saveProfessor(ProfessorViewModel entityViewModel);

    void deleteProfessor(Integer id);
}
