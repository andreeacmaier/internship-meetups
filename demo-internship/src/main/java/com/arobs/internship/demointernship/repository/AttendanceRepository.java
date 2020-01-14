package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Attendance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AttendanceRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void saveAttendance(Attendance attendance){
        Session session = sessionFactory.getCurrentSession();
        session.save(attendance);
    }

    public List<Attendance> findEventsAttendingForUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select a from Attendance a where user.id = (?1)";
        Query query = session.createQuery(queryString);
        query.setParameter(1, userId);
        return query.getResultList();
    }

    public Attendance findById(int attendanceId) {
        Session session = sessionFactory.getCurrentSession();
        return session.byId(Attendance.class).load(attendanceId);
    }

    public void giveFeedback(Attendance attendance) {
        Session session = sessionFactory.getCurrentSession();
        session.update(attendance);
    }

    public List<Integer> findAttendeesToBeAwarded() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select u.user_id from users u , attendance a, event e\n" +
                "where u.user_id = a.user_id\n" +
                "and a.event_id = e.event_id\n" +
                "and e.status = 1;";
        Query query = session.createNativeQuery(queryString);
        return query.getResultList();
    }
}
