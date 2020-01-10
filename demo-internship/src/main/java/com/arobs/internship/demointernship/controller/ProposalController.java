package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.service.proposal.ProposalService;
import com.arobs.internship.demointernship.service.proposal.ProposalVotesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(path = "delete/{id}")
    public void deleteProposal(@PathVariable int id) {
        proposalService.deleteProposal(id);
    }

    @GetMapping(path = "/proposalsTop")
    public ResponseEntity<List<ProposalVotesDTO>> getProposalsTop(@RequestParam int topSize){
        return ResponseEntity.ok(proposalService.getProposalTop(topSize));
    }

}
