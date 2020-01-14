package com.arobs.internship.demointernship.service.event;

import com.arobs.internship.demointernship.entity.Event;
import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.EventRepository;
import com.arobs.internship.demointernship.repository.factory.ProposalRepositoryFactory;
import com.arobs.internship.demointernship.repository.factory.UserRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import com.arobs.internship.demointernship.utils.AchievementConstants;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
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
import java.util.Objects;

@Component
@EnableAsync
public class EventObject {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ProposalRepositoryFactory proposalRepositoryFactory;

    @Autowired
    UserRepositoryFactory userRepositoryFactory;

    @Autowired
    EventMapper eventMapper;

    public EventDTO getEventById(int eventId) {
        Event event = eventRepository.findById(eventId);
        return eventMapper.mapFromEntityToDto(event);
    }

    public void saveEvent(int proposalId, int roomNumber, String date) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Proposal proposal = proposalRepository.findById(proposalId);

        if (Objects.isNull(proposal)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposal not found");
        }
        if (!isValidDate(date)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date format");
        }
        if (!isRoomAvailable(date, roomNumber)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is not available for the selected date.");
        }

        Event event = new Event(proposal, roomNumber, date);
        eventRepository.saveEvent(event);
        proposalRepository.deleteProposal(proposalId);
    }

    public void editEvent(int eventId, int roomNumber, String date, int maximumNumberOfPeople) {
        Event event = eventRepository.findById(eventId);

        if (Objects.isNull(event)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        if (!isValidDate(date)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong date format");
        }
        if (!isRoomAvailableToUpdate(date, roomNumber, eventId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is not available for the selected date.");
        }

        event.setDate(date);
        event.setRoom(roomNumber);
        event.setMaximumPeople(maximumNumberOfPeople);

        eventRepository.updateEvent(event);
    }

    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    private boolean isRoomAvailableToUpdate(String date, int room, int eventId) {
        List<Event> events = eventRepository.findAllByDate(date);
        for (Event event : events) {
            if (event.getRoom() == room && event.getId() != eventId) {
                return false;
            }
        }
        return true;
    }

    private boolean isRoomAvailable(String date, int room) {
        List<Event> events = eventRepository.findAllByDate(date);
        for (Event event : events) {
            if (event.getRoom() == room) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    @Scheduled(fixedRate = 5*60*1000)
    @Async
    public void awardUser() throws ParseException {
        List<Event> events = eventRepository.findAll();
        for (Event event:events) {
            if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getDate()).before(new Date()) && !event.isStatus()) {
                event.setStatus(true); //close event
                eventRepository.updateEvent(event); //save update to db
                awardOrganizer(event);
            }
        }
    }

    private void awardOrganizer(Event event){
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        User user = userRepository.findUserById(event.getUser().getId());
        if (event.getDifficulty().equals("easy")){
            user.setPoints(user.getPoints()+ AchievementConstants.EASY_PRESENTATION_POINTS);
        }
        if (event.getDifficulty().equals("medium")){
            user.setPoints(user.getPoints()+ AchievementConstants.MEDIUM_PRESENTATION_POINTS);
        }
        if (event.getDifficulty().equals("hard")){
            user.setPoints(user.getPoints()+ AchievementConstants.HARD_PRESENTATION_POINTS);
        }
        userRepository.updateUser(user);
    }

}
