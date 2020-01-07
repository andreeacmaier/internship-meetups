package com.arobs.internship.demointernship.service;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.VoteRepository;
import com.arobs.internship.demointernship.repository.factory.ProposalRepositoryFactory;
import com.arobs.internship.demointernship.repository.factory.UserRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteObject {

    @Autowired
    UserRepositoryFactory userRepositoryFactory;

    @Autowired
    ProposalRepositoryFactory proposalRepositoryFactory;

    @Autowired
    VoteRepository voteRepository;

    public void vote(int userId, int proposalId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.JDBC_REPOSITORY_TYPE);
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.JDBC_REPOSITORY_TYPE);

        User user = userRepository.findUserById(userId);
        Proposal proposal = proposalRepository.findById(proposalId);

        voteRepository.save(user, proposal);
    }
}
