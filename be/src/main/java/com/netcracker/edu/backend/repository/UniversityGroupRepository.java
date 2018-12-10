package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Professor;
import com.netcracker.edu.backend.entity.UniversityGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UniversityGroupRepository extends
        CrudRepository<UniversityGroup, Integer>,
        PagingAndSortingRepository<UniversityGroup, Integer> {
}
