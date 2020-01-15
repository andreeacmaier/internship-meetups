package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.AwardingHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AwardingHistoryRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void save(AwardingHistory awardingHistory) {
        Session session = sessionFactory.getCurrentSession();
        session.save(awardingHistory);
    }
}
