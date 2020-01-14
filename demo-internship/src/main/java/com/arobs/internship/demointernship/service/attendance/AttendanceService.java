package com.arobs.internship.demointernship.service.attendance;

import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface AttendanceService {
    boolean userAttendsEvent(int userId, int eventId);

    void attendEvent(int userId, int eventId);

    boolean userIsAllowedToGiveFeedback(int attendanceId) throws ParseException;

    void giveFeedback(int attendanceId, FeedbackDTO feedback);
}
