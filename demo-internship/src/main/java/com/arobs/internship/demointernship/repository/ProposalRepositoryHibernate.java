package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.service.proposal.ProposalVotesDTO;
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

    @Override
    public void deleteProposal(int id) {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "delete from Proposal p where id = (?1)";
        Query query = session.createQuery(queryString);
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    public List<Proposal> getProposals() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "from Proposal";
        Query query = session.createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public List<ProposalVotesDTO> getProposalsTopHavingSize(int topSize){
        Session session = sessionFactory.getCurrentSession();
        String queryString = "SELECT p.title, COUNT(v.proposal_id) as proposal_votes " +
                "FROM proposal p INNER JOIN vote v ON v.proposal_id = p.proposal_id " +
                "GROUP BY(v.proposal_id) " +
                "ORDER BY(proposal_votes) DESC ";
        Query query = session.createNativeQuery(queryString).setMaxResults(topSize);
        return query.getResultList();
    }
}
