package com.arobs.internship.demointernship.service.event;

import org.springframework.stereotype.Service;

@Service
public interface EventService {

    EventDTO getEventById(int eventId);

    void saveEvent(int proposalId, int roomNumber, String date);

    void editEvent(int eventId, int roomNumber, String date, int maximumNumberOfPeople);
}
