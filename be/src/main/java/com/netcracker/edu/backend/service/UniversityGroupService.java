package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.UniversityGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UniversityGroupService {
    UniversityGroup saveGroup(UniversityGroup entity);

    Optional<UniversityGroup> getGroupById(Integer id);

    void deleteGroup(Integer id);

    Page<UniversityGroup> getPage(Pageable pageable);
}
