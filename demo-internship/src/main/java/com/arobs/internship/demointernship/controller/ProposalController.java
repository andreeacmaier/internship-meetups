package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.service.proposal.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    ProposalService proposalService;

    @PostMapping(path = "/createProposal")
    public ResponseEntity<Void> createProposal(@RequestBody ProposalDTO proposalDTO) {
        proposalService.createProposal(proposalDTO);
        return (ResponseEntity<Void>) ResponseEntity.ok();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProposalDTO> getProposalById(@PathVariable int id) {
        return ResponseEntity.ok(proposalService.getUserById(id));
    }

}
