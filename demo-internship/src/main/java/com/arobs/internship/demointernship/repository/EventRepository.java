package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Event;
import com.arobs.internship.demointernship.entity.Proposal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class EventRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryHibernate.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Event findById(int eventId) {
        Session session = sessionFactory.getCurrentSession();
        Event event = session.byId(Event.class).load(eventId);
        return event;
    }

    public void saveEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.save(event);
    }
}
