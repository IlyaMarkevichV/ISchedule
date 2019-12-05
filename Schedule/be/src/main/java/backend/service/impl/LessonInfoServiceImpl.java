package backend.service.impl;

import backend.entity.LessonInfo;
import backend.repository.LessonInfoRepository;
import backend.service.LessonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonInfoServiceImpl implements LessonInfoService {

    private LessonInfoRepository repository;

    @Autowired
    public LessonInfoServiceImpl(LessonInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<LessonInfo> getAllLessonInfos() {
        return repository.findAll();
    }
}
