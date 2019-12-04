package fapi.service;

import fapi.models.LessonViewModel;
import fapi.models.RestPageImpl;

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
