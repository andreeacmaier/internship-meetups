package com.arobs.internship.demointernship.service.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    ProposalObject proposalObject;

    @Override
    @Transactional
    public void createProposal(ProposalDTO proposalDTO) {
        proposalObject.createProposal(proposalDTO);
    }

    @Override
    @Transactional
    public ProposalDTO getProposalById(int id) {
        return proposalObject.getProposalById(id);
    }

    @Override
    @Transactional
    public boolean voteProposal(int id, int userId) {
        return proposalObject.voteProposal(id, userId);
    }

    @Override
    @Transactional
    public void deleteProposal(int id) {
        proposalObject.deleteProposal(id);
    }
}
