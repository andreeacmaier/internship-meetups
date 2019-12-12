package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws ClassNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
