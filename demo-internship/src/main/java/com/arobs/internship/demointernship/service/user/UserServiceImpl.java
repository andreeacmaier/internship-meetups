package com.arobs.internship.demointernship.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserObject userObject;

    @Override
    public UserDTO getUserById(int id) throws ClassNotFoundException {
        return userObject.getUserById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userObject.getAllUsers();
    }


}
