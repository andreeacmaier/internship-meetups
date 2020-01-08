package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.proposal.ProposalService;
import com.arobs.internship.demointernship.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VoteController {

    @Autowired
    UserService userService;

    @Autowired
    ProposalService proposalService;

    @PostMapping(path = "/{userId}/proposals/{proposalId}")
    public ResponseEntity<String> vote(@PathVariable int userId, @PathVariable int proposalId) {
        if (userService.userVotedProposal(userId, proposalId)) {
            return ResponseEntity.badRequest().body("Proposal already voted");
        } else {
            userService.vote(userId, proposalId);
            return ResponseEntity.ok("User with id = " + userId + " voted proposal with id = " + proposalId);
        }
    }
}
