package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProposalRepositoryHibernate implements ProposalRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryHibernate.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Proposal proposal) {
        Session session = sessionFactory.getCurrentSession();
        session.save(proposal);
    }

    @Override
    public Proposal findById(int id) {
        LOGGER.info(" ==> FIND BY ID PROPOSAL");
        Session session = sessionFactory.getCurrentSession();
        return session.byId(Proposal.class).load(id);
    }
}
