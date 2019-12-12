package com.arobs.internship.demointernship.service;

import com.arobs.internship.demointernship.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserById(int id) throws ClassNotFoundException;

    List<User> getAllUsers();
}
