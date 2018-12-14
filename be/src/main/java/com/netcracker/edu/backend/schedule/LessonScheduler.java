package com.netcracker.edu.backend.schedule;

import com.netcracker.edu.backend.entity.Lesson;
import com.netcracker.edu.backend.entity.LessonDate;
import com.netcracker.edu.backend.repository.LessonDateRepository;
import com.netcracker.edu.backend.repository.ScheduleRepository;
import com.netcracker.edu.backend.resource.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class LessonScheduler {

    private static final Logger log = LoggerFactory.getLogger(LessonScheduler.class);

    private static final String[] days = {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    private ScheduleRepository scheduleRepository;
    private LessonDateRepository lessonDateRepository;

    @Autowired
    public LessonScheduler(ScheduleRepository scheduleRepository,
                           LessonDateRepository lessonDateRepository) {
        this.scheduleRepository = scheduleRepository;
        this.lessonDateRepository = lessonDateRepository;
    }

    /*@Scheduled(cron = "0 59 4 ? * SAT")*/
    public void addLessonsToLessonDateTable() {
        //get current week monday
        Date monday = Date.valueOf(LocalDate.now().with(DayOfWeek.MONDAY));

        for (byte dayNumber = 0; dayNumber < 6; dayNumber++) {
            log.info("Cycle - {}", (dayNumber + 1));
            log.info("Extract current day lessons from from database.");
            Iterable<Lesson> currentDayLessons =
                    scheduleRepository.getLessonsByDay_DayName(days[dayNumber]);

            //create list of lessons that we want to add
            //to week schedule for all groups
            log.info("Iterate through array of lessons.");
            ArrayList<LessonDate> lessonDates = new ArrayList<>();
            for (Lesson lesson : currentDayLessons) {
                lessonDates.add(new LessonDate(lesson, DateHelper.addNDays(monday, dayNumber)));
                log.info("  -Lesson-date created where lesson: {} and day: {}.", lesson.getId(), DateHelper.addNDays(monday, dayNumber));
            }

            log.info("Save all lesson-date's in table");
            lessonDateRepository.saveAll(lessonDates);
        }
    }
}
