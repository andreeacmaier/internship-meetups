package com.arobs.internship.demointernship.service;

import org.springframework.stereotype.Service;

@Service
public interface VoteService {

    public void vote(int userId, int proposalId);
}
