package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.UniversityGroup;
import com.netcracker.edu.backend.repository.UniversityGroupRepository;
import com.netcracker.edu.backend.service.UniversityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversityGroupServiceImpl implements UniversityGroupService {

    private UniversityGroupRepository repository;

    @Autowired
    public UniversityGroupServiceImpl(UniversityGroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public UniversityGroup saveGroup(UniversityGroup entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<UniversityGroup> getGroupById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UniversityGroup> getAllGroups() {
        return repository.findAll();
    }

    @Override
    public void deleteGroup(Integer id) {
        repository.deleteById(id);
    }
}
