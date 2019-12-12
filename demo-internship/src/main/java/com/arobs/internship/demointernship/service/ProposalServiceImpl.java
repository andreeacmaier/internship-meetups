package com.arobs.internship.demointernship.service;

import com.arobs.internship.demointernship.repository.ProposalRepository;
import com.arobs.internship.demointernship.service.dto.ProposalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    ProposalRepository proposalRepository;

    @Override
    public boolean createProposal(ProposalDTO proposal) {
        return proposalRepository.save(proposal);
    }

}
