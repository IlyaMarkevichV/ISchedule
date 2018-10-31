package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.UniversityGroup;

import java.util.Optional;

public interface UniversityGroupService {
    UniversityGroup saveGroupEntity(UniversityGroup entity);

    Optional<UniversityGroup> getGroupEntityById(Integer id);

    Iterable<UniversityGroup> getAllGroupEntities();

    void deleteGroupEntity(Integer id);
}
