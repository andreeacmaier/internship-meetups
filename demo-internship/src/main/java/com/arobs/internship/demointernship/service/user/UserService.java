package com.arobs.internship.demointernship.service.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO getUserById(int id) throws ClassNotFoundException;

    List<UserDTO> getAllUsers();
}
