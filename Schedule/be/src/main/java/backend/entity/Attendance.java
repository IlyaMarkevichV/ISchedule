package backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "attendance", schema = "project")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "lesson_date")
    private Date date;

    @Column(name = "attendance_status")
    @Min(1)
    @Max(3)
    private byte status;

    public Attendance() {
    }

    public Attendance(Student student, Lesson lesson, Date date, @Min(1) @Max(3) byte status) {
        this.student = student;
        this.lesson = lesson;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return id == that.id &&
                status == that.status &&
                student.equals(that.student) &&
                lesson.equals(that.lesson) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, lesson, date, status);
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", student=" + student.getId() +
                ", lesson=" + lesson.getLessonInfo().getLessonName() +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
