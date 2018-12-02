package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface LessonRepository extends
        CrudRepository<Lesson, Integer>,
        PagingAndSortingRepository<Lesson, Integer> {
    
}
