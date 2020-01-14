package com.arobs.internship.demointernship.service.attendance;

import com.arobs.internship.demointernship.entity.Attendance;
import com.arobs.internship.demointernship.entity.Event;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.AttendanceRepository;
import com.arobs.internship.demointernship.repository.EventRepository;
import com.arobs.internship.demointernship.repository.factory.UserRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import com.arobs.internship.demointernship.utils.AchievementConstants;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@EnableAsync
public class AttendanceObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceObject.class);

    @Autowired
    UserRepositoryFactory userRepositoryFactory;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    public boolean userAttendsEvent(int userId, int eventId) {
        Event event = eventRepository.findById(eventId);
        List<Attendance> eventAttending = attendanceRepository.findEventsAttendingForUser(userId);
        for (Attendance attendance : eventAttending) {
            if (attendance.getEvent().equals(event)) {
                return true;
            }
        }
        return false;
    }

    public void attendEvent(int userId, int eventId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        User user = userRepository.findUserById(userId);
        Event event = eventRepository.findById(eventId);
        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setEvent(event);
        attendanceRepository.saveAttendance(attendance);
    }

    public boolean userIsAllowedToGiveFeedback(int attendanceId) throws ParseException {
        Attendance attendance = attendanceRepository.findById(attendanceId);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(attendance.getEvent().getDate()).before(new Date());
    }

    public void giveFeedback(int attendanceId, FeedbackDTO feedback) {
        if (feedback.getNote() < 1 || feedback.getNote() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Note field should be between 1 and 5");
        }
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Attendance attendance = attendanceRepository.findById(attendanceId);
        attendance.setNote(feedback.getNote());
        attendance.setComment(feedback.getComment());
        attendanceRepository.update(attendance);
        User user = userRepository.findUserById(attendance.getUser().getId());
        user.setPoints(user.getPoints() + AchievementConstants.EVENT_FEEDBACK_POINTS);
        userRepository.updateUser(user);
    }

    @Transactional
    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Async
    public void awardAttendee() {
        LOGGER.info("AWARD ATTENDEE");
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        List<Attendance> attendances = attendanceRepository.findAll();
        for (Attendance attendance : attendances) {
            if (!attendance.isUserAwarded()) {
                User user = userRepository.findUserById(attendance.getUser().getId());
                Event event = eventRepository.findById(attendance.getEvent().getId());
                if (event.isClose()) {
                    user.setPoints(user.getPoints() + AchievementConstants.ATTENDEE_POINTS);
                    userRepository.updateUser(user);
                    attendance.setUserAwarded(true);
                    attendanceRepository.update(attendance);
                }
            }
        }
    }
}
