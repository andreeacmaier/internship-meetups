package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @PostMapping (path = "/{userId}/proposals/{proposalId}")
    public void vote(@PathVariable int userId, @PathVariable int proposalId) {
        voteService.vote(userId, proposalId);
    }
}
