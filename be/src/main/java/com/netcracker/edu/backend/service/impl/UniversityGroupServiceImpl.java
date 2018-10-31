package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.UniversityGroup;
import com.netcracker.edu.backend.repository.UniversityGroupRepository;
import com.netcracker.edu.backend.service.UniversityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniversityGroupServiceImpl implements UniversityGroupService {

    @Autowired
    private UniversityGroupRepository repository;

    @Override
    public UniversityGroup saveGroupEntity(UniversityGroup entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<UniversityGroup> getGroupEntityById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UniversityGroup> getAllGroupEntities() {
        return repository.findAll();
    }

    @Override
    public void deleteGroupEntity(Integer id) {
        repository.deleteById(id);
    }
}
