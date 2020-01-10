package com.arobs.internship.demointernship.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    EventObject eventObject;

    @Override
    @Transactional
    public EventDTO getEventById(int eventId) {
        return eventObject.getEventById(eventId);
    }

    @Override
    @Transactional
    public void saveEvent(int proposalId, int roomNumber, Date date) {
        eventObject.saveEvent(proposalId, roomNumber, date);
    }
}
