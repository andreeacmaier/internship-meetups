package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.user.UserDTO;
import com.arobs.internship.demointernship.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        boolean created = userService.createUser(userDTO);
        if (created) {
            return ResponseEntity.created(URI.create("/")).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
