package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.AttendanceViewModel;
import com.netcracker.edu.fapi.service.AttendanceDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

@Service
public class AttendanceDataServiceImpl implements AttendanceDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public AttendanceViewModel save(AttendanceViewModel attendance) {
        return attendance != null ?
                new RestTemplate().postForEntity(
                        backendServerUrl + "/api/attendance",
                        attendance,
                        AttendanceViewModel.class).getBody()
                :
                null;
    }

    @Override
    public List<AttendanceViewModel> saveAll(List<AttendanceViewModel> attendances) {
        if (attendances != null && !attendances.isEmpty()) {
            HttpEntity<List<AttendanceViewModel>> httpEntity = new HttpEntity<>(attendances);

            return new RestTemplate().exchange(
                    backendServerUrl + "/api/attendance/list",
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<List<AttendanceViewModel>>() {
                    }).getBody();
        }
        return null;
    }

    @Override
    public List<AttendanceViewModel> getAttendancesByStatusAndStudentIdAndDateBetween(byte status, int studentId, Date from, Date to) {
        return new RestTemplate().exchange(
                backendServerUrl +
                        String.format("/api/attendance/student?status=%d&student_id=%d&from=%s&to=%s",
                                status, studentId, from, to),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AttendanceViewModel>>() {
                }).getBody();
    }

    @Override
    public List<AttendanceViewModel> getAttendancesByStatusAndGroupIdAndLessonIdAndDateBetween(byte status, int groupId, int lessonId, Date from, Date to) {
        return new RestTemplate().exchange(
                backendServerUrl +
                        String.format("/api/attendance/group?status=%d&group_id=%d&lesson_id=%d&from=%s&to=%s",
                                status, groupId, lessonId, from, to),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AttendanceViewModel>>() {
                }).getBody();
    }
}
