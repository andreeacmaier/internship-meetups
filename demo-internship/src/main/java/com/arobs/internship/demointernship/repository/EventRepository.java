package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Event> findAllByDate(String date) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select e from Event e where date = (?1)";
        Query query = session.createQuery(queryString);
        query.setParameter(1, date);
        return query.getResultList();
    }

    public void updateEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(event);
    }

    public List<Event> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Event e");
        return query.getResultList();
    }
}
