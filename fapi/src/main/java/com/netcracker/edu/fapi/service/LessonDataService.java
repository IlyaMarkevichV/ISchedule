package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.LessonViewModel;
import com.netcracker.edu.fapi.service.impl.RestPageImpl;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LessonDataService {

    LessonViewModel getLessonById(Integer id);

    LessonViewModel saveLesson(LessonViewModel schedule);

    void deleteLesson(Integer id);

    RestPageImpl<LessonViewModel> getPage(HttpServletRequest request);
}
