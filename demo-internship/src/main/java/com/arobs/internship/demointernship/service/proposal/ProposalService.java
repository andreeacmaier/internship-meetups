package com.arobs.internship.demointernship.service.proposal;

import org.springframework.stereotype.Service;

@Service
public interface ProposalService {

    boolean createProposal(ProposalDTO proposalDTO);
}
