package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.FacultyViewModel;

import java.util.List;

public interface FacultyDataService {
    List<FacultyViewModel> getAllFaculties();

    FacultyViewModel getFacultyById(Integer id);

    FacultyViewModel saveFaculty(FacultyViewModel faculty);

    void deleteFaculty(Integer id);
}
