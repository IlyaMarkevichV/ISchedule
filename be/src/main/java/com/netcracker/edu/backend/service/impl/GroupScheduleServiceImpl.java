package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.GroupSchedule;
import com.netcracker.edu.backend.repository.GroupScheduleRepository;
import com.netcracker.edu.backend.service.GroupScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupScheduleServiceImpl implements GroupScheduleService {

    private GroupScheduleRepository repository;

    @Autowired
    public GroupScheduleServiceImpl(GroupScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public GroupSchedule saveLectureOfGroup(GroupSchedule groupSchedule) {
        return repository.save(groupSchedule);
    }

    @Override
    public Optional<GroupSchedule> getLectureOfGroupById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<GroupSchedule> getAllLecturesOfGroups() {
        return repository.findAll();
    }

    @Override
    public void deleteLectureOfGroup(Integer id) {
        repository.deleteById(id);
    }
}
