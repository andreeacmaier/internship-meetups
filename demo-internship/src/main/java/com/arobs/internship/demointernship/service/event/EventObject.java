package com.arobs.internship.demointernship.service.event;

import com.arobs.internship.demointernship.entity.Event;
import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.EventRepository;
import com.arobs.internship.demointernship.repository.factory.ProposalRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class EventObject {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ProposalRepositoryFactory proposalRepositoryFactory;

    @Autowired
    EventMapper eventMapper;

    public EventDTO getEventById(int eventId) {
        Event event = eventRepository.findById(eventId);
        return eventMapper.mapFromEntityToDto(event);
    }

    public void saveEvent(int proposalId, int roomNumber, Date date) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Proposal proposal = proposalRepository.findById(proposalId);
        Event event = new Event(proposal, roomNumber, date);
        eventRepository.saveEvent(event);
    }
}
