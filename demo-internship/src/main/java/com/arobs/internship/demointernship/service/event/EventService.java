package com.arobs.internship.demointernship.service.event;

import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public interface EventService {

    EventDTO getEventById(int eventId);

    void saveEvent(int proposalId, int roomNumber, Date date);
}
