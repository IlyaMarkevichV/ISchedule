package backend.service.impl;

import backend.entity.LessonTime;
import backend.repository.LessonTimeRepository;
import backend.service.LessonTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonTimeServiceImpl implements LessonTimeService {

    private LessonTimeRepository repository;

    @Autowired
    public LessonTimeServiceImpl(LessonTimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<LessonTime> getAllLessonTimes() {
        return repository.findAll();
    }
}
