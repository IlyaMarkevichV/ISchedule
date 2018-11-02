package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.GroupSubject;
import com.netcracker.edu.backend.repository.GroupSubjectRepository;
import com.netcracker.edu.backend.service.GroupSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupSubjectServiceImpl implements GroupSubjectService {

    private GroupSubjectRepository repository;

    @Autowired
    public GroupSubjectServiceImpl(GroupSubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public GroupSubject saveGroupSubject(GroupSubject subject) {
        return repository.save(subject);
    }

    @Override
    public Optional<GroupSubject> getGroupSubjectById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<GroupSubject> getAllGroupSubjects() {
        return repository.findAll();
    }

    @Override
    public void deleteGroupSubject(Integer id) {
        repository.deleteById(id);
    }
}
