package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.UniversityGroup;

import java.util.Optional;

public interface UniversityGroupService {
    UniversityGroup saveGroup(UniversityGroup entity);

    Optional<UniversityGroup> getGroupById(Integer id);

    Iterable<UniversityGroup> getAllGroups();

    void deleteGroup(Integer id);
}
