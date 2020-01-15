package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.attendance.AttendanceService;
import com.arobs.internship.demointernship.service.attendance.FeedbackDTO;
import com.arobs.internship.demointernship.service.proposal.ProposalService;
import com.arobs.internship.demointernship.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;

@Controller
public class AttendanceController {

    @Autowired
    ProposalService proposalService;

    @Autowired
    UserService userService;

    @Autowired
    AttendanceService attendanceService;

    @PostMapping(path = "/{userId}/attendance/{eventId}")
    public ResponseEntity<String> attendEvent(@PathVariable int userId, @PathVariable int eventId) {
        if (attendanceService.userAttendsEvent(userId, eventId)) {
            return ResponseEntity.badRequest().body("User already registered for this event.");
        } else {
            attendanceService.attendEvent(userId, eventId);
            return ResponseEntity.ok("User with id = " + userId + " attends event with id = " + eventId);
        }
    }

    @PutMapping(path = "/attendance/{attendanceId}")
    public ResponseEntity<FeedbackDTO> giveFeedback(@PathVariable int attendanceId, @RequestBody FeedbackDTO feedback) throws ParseException {
        if (attendanceService.userIsAllowedToGiveFeedback(attendanceId)){
            attendanceService.giveFeedback(attendanceId, feedback);
        }
        return ResponseEntity.ok(feedback);
    }

}
