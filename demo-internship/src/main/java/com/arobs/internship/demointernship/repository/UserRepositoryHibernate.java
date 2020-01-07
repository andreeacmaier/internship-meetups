package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryHibernate implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryHibernate.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.byId(User.class).load(id);
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String query = "select u from User u";
        return session.createQuery(query).list();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public List<Proposal> findProposalsForUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select p from Proposal p where p.user.id = (?1)";
        Query query = session.createQuery(queryString);
        query.setParameter(1, userId);
        List<Proposal> proposals = query.getResultList();
        LOGGER.info("ID from db : " + proposals.get(0).getUser().getId());
        return proposals;
    }

    @Override
    public List findVotedProposalsForUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select u.votedProposals from User u where u.id = (?1)";
        Query query = session.createQuery(queryString);
        query.setParameter(1, userId);
        return query.getResultList();
    }
}
