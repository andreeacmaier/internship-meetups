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
    public void createProposal(@RequestBody ProposalDTO proposalDTO) {
        proposalService.createProposal(proposalDTO);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProposalDTO> getProposalById(@PathVariable int id) {
        return ResponseEntity.ok(proposalService.getProposalById(id));
    }

    @GetMapping(path = "{id}/vote")
    public ResponseEntity<Boolean> voteProposal(@PathVariable int id) {
        int userId = 1;
        return ResponseEntity.ok(proposalService.voteProposal(id, userId));
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteProposal(@PathVariable int id) {
        proposalService.deleteProposal(id);
    }

}
