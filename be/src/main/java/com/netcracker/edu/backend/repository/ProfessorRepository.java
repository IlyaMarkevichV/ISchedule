package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends
        CrudRepository<Professor, Integer>,
        PagingAndSortingRepository<Professor, Integer> {

}
