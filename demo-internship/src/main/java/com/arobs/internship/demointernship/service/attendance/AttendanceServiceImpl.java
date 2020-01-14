package com.arobs.internship.demointernship.service.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Component
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceObject attendanceObject;

    @Override
    @Transactional
    public boolean userAttendsEvent(int userId, int eventId) {
        return attendanceObject.userAttendsEvent(userId, eventId);
    }

    @Override
    @Transactional
    public void attendEvent(int userId, int eventId) {
        attendanceObject.attendEvent(userId, eventId);
    }

    @Override
    @Transactional
    public boolean userIsAllowedToGiveFeedback(int attendanceId) throws ParseException {
        return attendanceObject.userIsAllowedToGiveFeedback(attendanceId);
    }

    @Override
    @Transactional
    public void giveFeedback(int attendanceId, FeedbackDTO feedback) {
        attendanceObject.giveFeedback(attendanceId, feedback);
    }
}
