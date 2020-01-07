package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoteRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryHibernate.class);

    @Autowired
    private SessionFactory sessionFactory;

    // TODO: 1/7/2020 param: userId, proposalId; select user, propsal => save vote 
    public void save(User user, Proposal proposal) {
        Session session = sessionFactory.getCurrentSession();
        user.getVotedProposals().add(proposal);
        session.save(user);
    }
}
