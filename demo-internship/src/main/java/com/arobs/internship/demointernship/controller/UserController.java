package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.service.user.UserDTO;
import com.arobs.internship.demointernship.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) throws ClassNotFoundException {
        UserDTO user = userService.getUserById(userId);
        if (Objects.isNull(user)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(path = "/{userId}/createdProposals")
    public ResponseEntity<List<ProposalDTO>> getProposalsForUser(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getProposalsForUser(userId));
    }

    @GetMapping(path = {"/{userId}/votedProposals"})
    public ResponseEntity<List<ProposalDTO>> getVotedProposalsForUser(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getVotedProposalsForUser(userId));
    }
}
