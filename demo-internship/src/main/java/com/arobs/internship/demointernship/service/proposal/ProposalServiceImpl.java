package com.arobs.internship.demointernship.service.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    ProposalObject proposalObject;

    @Override
    public boolean createProposal(ProposalDTO proposalDTO) {
        return proposalObject.createProposal(proposalDTO);
    }

}
