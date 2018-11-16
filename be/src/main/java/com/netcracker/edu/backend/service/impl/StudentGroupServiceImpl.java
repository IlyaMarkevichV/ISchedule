package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Account;
import com.netcracker.edu.backend.entity.StudentGroup;
import com.netcracker.edu.backend.repository.StudentGroupRepository;
import com.netcracker.edu.backend.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {

    private StudentGroupRepository repository;

    @Autowired
    public StudentGroupServiceImpl(StudentGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentGroup saveStudent(StudentGroup studentGroup) {
        return repository.save(studentGroup);
    }

    @Override
    public Optional<StudentGroup> getStudentGroupById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<StudentGroup> getAllStudentGroups() {
        return repository.findAll();
    }

    @Override
    public void deleteStudentByAccountId(Integer id) {
        repository.deleteStudentGroupByAccount_AccountId(id);
    }
}
