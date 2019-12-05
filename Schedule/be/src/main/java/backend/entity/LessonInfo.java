package backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lesson_info", schema = "project")
public class LessonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lesson_info_id")
    private int id;

    @Column(name = "lesson_name")
    private String lessonName;

    public LessonInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonInfo lessonInfo = (LessonInfo) o;
        return id == lessonInfo.id &&
                lessonName.equals(lessonInfo.lessonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonName);
    }
}
