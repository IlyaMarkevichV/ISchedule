package backend.service;

import backend.entity.Faculty;

import java.util.Optional;

public interface FacultyService {
    void deleteFaculty(Integer id);

    Optional<Faculty> getFacultyById(Integer id);

    Iterable<Faculty> getAllFaculties();

    Faculty saveFaculty(Faculty faculty);
}
