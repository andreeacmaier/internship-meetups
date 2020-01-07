package com.arobs.internship.demointernship.service.proposal;

import org.springframework.stereotype.Service;

@Service
public interface ProposalService {

    void createProposal(ProposalDTO proposalDTO);

    ProposalDTO getProposalById(int id);

    boolean voteProposal(int id, int userId);

    void deleteProposal(int id);
}
