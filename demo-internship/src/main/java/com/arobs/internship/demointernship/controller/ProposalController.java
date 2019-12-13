package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.proposal.ProposalService;
import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    ProposalService proposalService;

    @PostMapping(path = "/new")
    public ResponseEntity<Void> createProposal(@RequestBody ProposalDTO proposalDTO) {
        boolean created = proposalService.createProposal(proposalDTO);
        if (created) {
            return ResponseEntity.created(URI.create("/")).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
