package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.StudentGroup;
import org.springframework.data.repository.CrudRepository;

public interface StudentGroupRepository extends CrudRepository<StudentGroup, Integer> {

    void deleteStudentGroupByAccount_AccountId(Integer id);
}
