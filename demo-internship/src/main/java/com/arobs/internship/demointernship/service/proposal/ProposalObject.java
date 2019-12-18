package com.arobs.internship.demointernship.service.proposal;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.ProposalRepositoryJDBC;
import com.arobs.internship.demointernship.repository.factory.ProposalRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalObject {

    @Autowired
    ProposalRepositoryFactory proposalRepositoryFactory;

    @Autowired
    ProposalMapper proposalMapper;

    public boolean createProposal(ProposalDTO proposalDTO) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.JDBC_REPOSITORY_TYPE);
        if (proposalDTO != null) {
            Proposal proposal = proposalMapper.map(proposalDTO, Proposal.class);
            return proposalRepository.save(proposal);
        }
        return false;
    }
}
