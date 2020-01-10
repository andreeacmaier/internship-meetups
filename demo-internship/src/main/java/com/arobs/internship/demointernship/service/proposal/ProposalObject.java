package com.arobs.internship.demointernship.service.proposal;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.factory.ProposalRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProposalObject {

    @Autowired
    ProposalRepositoryFactory proposalRepositoryFactory;

    @Autowired
    ProposalMapper proposalMapper;

    public void createProposal(ProposalDTO proposalDTO) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        if (proposalDTO != null) {
            Proposal proposal = proposalMapper.map(proposalDTO, Proposal.class);
            proposalRepository.save(proposal);
        }
    }

    public ProposalDTO getProposalById(int id) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Proposal proposal = proposalRepository.findById(id);
        if (proposal != null) {
            return proposalMapper.map(proposal, ProposalDTO.class);
        }
        return null;
    }

    public void deleteProposal(int id) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        proposalRepository.deleteProposal(id);
    }
    
    public List<ProposalVotesDTO> getProposalTop(int topSize) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        return proposalRepository.getProposalsTopHavingSize(topSize);
    }

    public List<ProposalDTO> getAllProposals() {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        List<Proposal> proposals = proposalRepository.getProposals();
        if (proposals != null){
            return proposalMapper.mapAsList(proposals, ProposalDTO.class);
        }
        return null;
    }
}
