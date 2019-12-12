package com.arobs.internship.demointernship.service;

import com.arobs.internship.demointernship.service.dto.ProposalDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProposalService {

    boolean createProposal(ProposalDTO proposal);
}
