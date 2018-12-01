package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "group_info", schema = "backend")
public class GroupInfo {
    @Id
    @Column(name = "group_info_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UniversityGroup group;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    // TODO: util.date or sql.timestamp ?
    @Column(name = "message_datetime")
    private Timestamp message_date;

    public GroupInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UniversityGroup getGroup() {
        return group;
    }

    public void setGroup(UniversityGroup group) {
        this.group = group;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getMessage_date() {
        return message_date;
    }

    public void setMessage_date(Timestamp message_date) {
        this.message_date = message_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupInfo groupInfo = (GroupInfo) o;
        return id == groupInfo.id &&
                Objects.equals(group, groupInfo.group) &&
                Objects.equals(professor, groupInfo.professor) &&
                Objects.equals(message, groupInfo.message) &&
                Objects.equals(message_date, groupInfo.message_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, professor, message, message_date);
    }
}
