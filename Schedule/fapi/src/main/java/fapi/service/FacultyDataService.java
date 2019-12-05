package fapi.service;

import fapi.models.FacultyViewModel;

import java.util.List;

public interface FacultyDataService {
    List<FacultyViewModel> getAllFaculties();

    FacultyViewModel getFacultyById(Integer id);

    FacultyViewModel saveFaculty(FacultyViewModel faculty);

    void deleteFaculty(Integer id);
}
