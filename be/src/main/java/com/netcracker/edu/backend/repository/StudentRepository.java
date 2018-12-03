package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends
        CrudRepository<Student, Integer>,
        PagingAndSortingRepository<Student, Integer> {
}
