package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
