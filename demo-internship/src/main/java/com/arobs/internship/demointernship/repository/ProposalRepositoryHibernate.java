package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProposalRepositoryHibernate implements ProposalRepository {

    @Override
    public boolean save(Proposal proposal) {
        return false;
    }
}
