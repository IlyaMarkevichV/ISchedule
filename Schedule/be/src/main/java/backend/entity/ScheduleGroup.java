package backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "schedule_group", schema = "project")
public class ScheduleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UniversityGroup group;

    public ScheduleGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public UniversityGroup getGroup() {
        return group;
    }

    public void setGroup(UniversityGroup group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleGroup that = (ScheduleGroup) o;
        return id == that.id &&
                lesson.equals(that.lesson) &&
                group.equals(that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lesson, group);
    }
}
