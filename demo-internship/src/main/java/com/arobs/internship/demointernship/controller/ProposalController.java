package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.service.proposal.ProposalService;
import com.arobs.internship.demointernship.service.proposal.ProposalVotesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    ProposalService proposalService;

    @PostMapping(path = "/createProposal")
    public void createProposal(@RequestBody ProposalDTO proposalDTO) {
        proposalService.createProposal(proposalDTO);
    }

    @GetMapping(path = "{proposalId}")
    public ResponseEntity<ProposalDTO> getProposalById(@PathVariable int proposalId) {
        ProposalDTO proposal = proposalService.getProposalById(proposalId);
        if (Objects.isNull(proposal)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(proposal);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<ProposalDTO>> getAllProposals(){
        return ResponseEntity.ok(proposalService.getAllProposals());
    }

    @DeleteMapping(path = "delete/{proposalId}")
    public void deleteProposal(@PathVariable int proposalId) {
        proposalService.deleteProposal(proposalId);
    }

    @GetMapping(path = "/proposalsTop")
    public ResponseEntity<List<ProposalVotesDTO>> getProposalsTop(@RequestParam int topSize) {
        return ResponseEntity.ok(proposalService.getProposalTop(topSize));
    }

}
