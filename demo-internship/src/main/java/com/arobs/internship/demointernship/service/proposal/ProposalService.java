package com.arobs.internship.demointernship.service.proposal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProposalService {

    void createProposal(ProposalDTO proposalDTO);

    ProposalDTO getProposalById(int id);

    void deleteProposal(int id);

    List<ProposalVotesDTO> getProposalTop(int topSize);
}
