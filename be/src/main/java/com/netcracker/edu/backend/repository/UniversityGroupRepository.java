package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.UniversityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UniversityGroupRepository extends
        CrudRepository<UniversityGroup, Integer>,
        PagingAndSortingRepository<UniversityGroup, Integer> {
}
