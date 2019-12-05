package backend.schedule;

import backend.entity.Attendance;
import backend.entity.Lesson;
import backend.entity.LessonDate;
import backend.entity.Student;
import backend.repository.*;
import backend.resource.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LessonScheduler {

    private static final Logger log = LoggerFactory.getLogger(LessonScheduler.class);

    public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    private ScheduleRepository scheduleRepository;
    private LessonDateRepository lessonDateRepository;
    private StudentRepository studentRepository;
    private ScheduleGroupRepository scheduleGroupRepository;
    private AttendanceRepository attendanceRepository;

    @Autowired
    public LessonScheduler(ScheduleRepository scheduleRepository,
                           LessonDateRepository lessonDateRepository,
                           StudentRepository studentRepository,
                           ScheduleGroupRepository scheduleGroupRepository,
                           AttendanceRepository attendanceRepository) {
        this.scheduleRepository = scheduleRepository;
        this.lessonDateRepository = lessonDateRepository;
        this.studentRepository = studentRepository;
        this.scheduleGroupRepository = scheduleGroupRepository;
        this.attendanceRepository = attendanceRepository;
    }

    //real: "0 5 0 ? * MON"
    @Scheduled(cron = "0 07 0 ? * THU")
    public void addLessonsToLessonDateTable() {
        //get current week monday
        Date monday = Date.valueOf(LocalDate.now().with(DayOfWeek.MONDAY));

        for (byte dayNumber = 0; dayNumber < 6; dayNumber++) {
            log.info("Day - {}", (dayNumber + 1));
            log.info("Extract current day lessons from from database.");
            Iterable<Lesson> currentDayLessons =
                    scheduleRepository.getLessonsByDay_DayName(DAYS[dayNumber]);

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

    /**
     * real: "30 42 20 ? * MON-SAT"
     * нужно добавить каждому студенту из группы его метку о посещении.
     * 1) возвращаем список всех студентов
     * 2) итерируем по нему
     * 3) итерируя, находим по params: DayOfWeek, Group для студента
     * 4) создаем посещения
     */
    @Scheduled(cron = "30 07 0 ? * THU")
    public void addAttendanceForEveryStudentAtCurrentDay() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        Date currentDate = Date.valueOf(LocalDate.now());
        Iterable<Student> students = studentRepository.findAll();

        for (Student student : students) {
            Iterable<Lesson> studentLessons =
                    scheduleGroupRepository.getLessonsByGroupIdAndDayOfWeek(
                            DAYS[currentDay.getValue() - 1], student.getGroup().getId());

            List<Attendance> studentAttendances = new ArrayList<Attendance>();
            studentLessons.forEach(lesson ->
                    studentAttendances.add(new Attendance(student, lesson, currentDate, (byte) 3)));

            attendanceRepository.saveAll(studentAttendances);
        }
    }
}