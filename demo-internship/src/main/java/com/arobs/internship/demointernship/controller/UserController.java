package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.service.user.UserDTO;
import com.arobs.internship.demointernship.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) throws ClassNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(path = "/{id}/createdProposals")
    public ResponseEntity<List<ProposalDTO>> getProposalsForUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getProposalsForUser(id));
    }

    @GetMapping(path = {"/{id}/votedProposals"})
    public ResponseEntity<List<ProposalDTO>> getVotedProposalsForUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getVotedProposalsForUser(id));
    }
}
