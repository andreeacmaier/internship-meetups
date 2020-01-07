package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        Session session = sessionFactory.getCurrentSession();
        return session.byId(Proposal.class).load(id);
    }

    // TODO: ce trebuie transmis ca parametru pentru a se insera in tabela de Votes
    @Override
    public void voteProposal(int id, int userId) {
       /* Session session = sessionFactory.getCurrentSession();
        String queryString = "select p from Proposal p where id = (?1)";
        Query query = session.createQuery(queryString);
        query.setParameter(1,id);
        Proposal proposal = (Proposal) query.getSingleResult();
        proposal.getUsers().add(user);*/
    }

    @Override
    public void deleteProposal(int id) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "delete from Proposal p where id = (?1)";
        Query query = session.createQuery(queryString);
        query.setParameter(1,id);
        query.executeUpdate();
    }
}
