package com.arobs.internship.demointernship.service.proposal;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalObject {

    @Autowired
    ProposalRepository proposalRepository;

    @Autowired
    ProposalMapper proposalMapper;

    public boolean createProposal(ProposalDTO proposalDTO) {
        if (proposalDTO != null) {
            Proposal proposal = proposalMapper.map(proposalDTO, Proposal.class);
            return proposalRepository.save(proposal);
        }
        return false;
    }
}
