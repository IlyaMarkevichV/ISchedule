package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Account;
import com.netcracker.edu.backend.entity.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends
        CrudRepository<Professor, Integer>,
        PagingAndSortingRepository<Professor, Integer> {

    Optional<Professor> findProfessorByAccount_Login(String login);
}
