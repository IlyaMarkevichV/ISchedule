package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.LessonViewModel;
import com.netcracker.edu.fapi.models.RestPageImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

public interface LessonDataService {

    LessonViewModel getLessonById(Integer id);

    LessonViewModel saveLesson(LessonViewModel schedule);

    void deleteLesson(Integer id);

    RestPageImpl<LessonViewModel> getPage(HttpServletRequest request);

    List<LessonViewModel> getProfessorLessonsBetween(Integer professorId, Date from);

    List<LessonViewModel> getGroupLessonsBetween(Integer groupId, Date from);
}
