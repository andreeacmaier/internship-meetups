package com.arobs.internship.demointernship.service.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public void deleteProposal(int id) {
        proposalObject.deleteProposal(id);
    }

    @Override
    @Transactional
    public List<ProposalVotesDTO> getProposalTop(int topSize) {
        return proposalObject.getProposalTop(topSize);
    }

    @Override
    @Transactional
    public List<ProposalDTO> getAllProposals() {
        return proposalObject.getAllProposals();
    }
}
