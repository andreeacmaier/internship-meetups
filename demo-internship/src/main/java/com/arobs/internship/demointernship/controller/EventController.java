package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.event.EventDTO;
import com.arobs.internship.demointernship.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping(path = "{proposalId}/createEvent")
    public ResponseEntity saveEvent(@PathVariable int proposalId, @RequestParam int roomNumber,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String date) {
        eventService.saveEvent(proposalId, roomNumber, date);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping(path = "{eventId}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable int eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @PutMapping(path = "{eventId}")
    public ResponseEntity editEvent(@PathVariable int eventId,
                                    @RequestParam int roomNumber,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String date,
                                    @RequestParam int maximumNumberOfPeople){
        eventService.editEvent(eventId, roomNumber, date, maximumNumberOfPeople);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
