package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Prize;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PrizeRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Prize prize) {
        Session session = sessionFactory.getCurrentSession();
        session.save(prize);
    }

    public Prize findById(int prizeId) {
        Session session = sessionFactory.getCurrentSession();
        return session.byId(Prize.class).load(prizeId);
    }
}
