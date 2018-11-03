package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Schedule;
import com.netcracker.edu.backend.repository.ScheduleRepository;
import com.netcracker.edu.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository repository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Schedule saveLectureInSchedule(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public Optional<Schedule> getLectureFromScheduleById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Schedule> getAllLecturesFromSchedule() {
        return repository.findAll();
    }

    @Override
    public void deleteLectureFromSchedule(Integer id) {
        repository.deleteById(id);
    }
}
