package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.ProfessorEntityViewModel;

import java.util.List;

public interface ProfessorEntityDataService {
    List<ProfessorEntityViewModel> getAll();

    ProfessorEntityViewModel getProfessorEntityById(Integer id);

    ProfessorEntityViewModel saveProfessorEntity(ProfessorEntityViewModel entityViewModel);

    void deleteProfessorEntity(Integer id);
}
