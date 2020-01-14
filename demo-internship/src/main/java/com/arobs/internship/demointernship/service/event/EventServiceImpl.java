package com.arobs.internship.demointernship.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public void saveEvent(int proposalId, int roomNumber, String date) {
        eventObject.saveEvent(proposalId, roomNumber, date);
    }

    @Override
    @Transactional
    public void editEvent(int eventId, int roomNumber, String date, int maximumNumberOfPeople) {
        eventObject.editEvent(eventId, roomNumber, date, maximumNumberOfPeople);
    }
}
