package com.arobs.internship.demointernship.service;

import com.arobs.internship.demointernship.repository.VoteRepository;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteObject voteObject;

    @Override
    @Transactional
    public void vote(int userId, int proposalId) {
        voteObject.vote(userId, proposalId);
    }

}
