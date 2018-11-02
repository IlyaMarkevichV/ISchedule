package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.GroupSubject;

import java.util.Optional;

public interface GroupSubjectService {
    GroupSubject saveGroupSubject(GroupSubject subject);

    Optional<GroupSubject> getGroupSubjectById(Integer id);

    Iterable<GroupSubject> getAllGroupSubjects();

    void deleteGroupSubject(Integer id);
}
