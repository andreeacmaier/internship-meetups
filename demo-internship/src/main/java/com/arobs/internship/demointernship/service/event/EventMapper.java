package com.arobs.internship.demointernship.service.event;

import com.arobs.internship.demointernship.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDTO mapFromEntityToDto(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setUser(event.getUser());
        eventDTO.setDate(event.getDate());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDifficulty(event.getDifficulty());
        eventDTO.setDurationInMinutes(event.getDurationInMinutes());
        eventDTO.setLanguage(event.getLanguage());
        eventDTO.setMaximumPeople(event.getMaximumPeople());
        eventDTO.setRoom(event.getRoom());
        eventDTO.setTitle(event.getTitle());

        return eventDTO;
    }

    public Event mapFromDtoToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setUser(eventDTO.getUser());
        event.setDate(eventDTO.getDate());
        event.setDescription(eventDTO.getDescription());
        event.setDifficulty(eventDTO.getDifficulty());
        event.setDurationInMinutes(eventDTO.getDurationInMinutes());
        event.setLanguage(eventDTO.getLanguage());
        event.setMaximumPeople(eventDTO.getMaximumPeople());
        event.setRoom(eventDTO.getRoom());
        event.setTitle(eventDTO.getTitle());

        return event;
    }

}
