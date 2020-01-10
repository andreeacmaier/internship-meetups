package com.arobs.internship.demointernship.repository.interfaces;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.service.proposal.ProposalVotesDTO;

import java.util.List;

public interface ProposalRepository {

    void save(Proposal proposal);

    Proposal findById(int id);

    void deleteProposal(int id);

    List<Proposal> getProposals();

    List<ProposalVotesDTO> getProposalsTopHavingSize(int topSize);
}
