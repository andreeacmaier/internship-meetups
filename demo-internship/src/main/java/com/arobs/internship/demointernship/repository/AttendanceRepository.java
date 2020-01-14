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

    public void update(Attendance attendance) {
        Session session = sessionFactory.getCurrentSession();
        session.update(attendance);
    }

    public List<Attendance> findAttendeesToBeAwarded() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select a.* " +
                " from users u , attendance a, event e " +
                " where u.user_id = a.user_id " +
                " and a.event_id = e.event_id " +
                " and e.status = 1 and a.user_awarded = 0;";
        Query query = session.createNativeQuery(queryString);
        return query.getResultList();
    }

    public List<Attendance> findAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Attendance  a");
        return query.getResultList();
    }
}
