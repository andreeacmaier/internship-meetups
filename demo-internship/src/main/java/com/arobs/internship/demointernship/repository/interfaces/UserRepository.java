package com.arobs.internship.demointernship.repository.interfaces;

import com.arobs.internship.demointernship.entity.User;

import java.util.List;

public interface UserRepository {

    User findUserById(int id);

    List<User> findAll();

    boolean save(User user);

}
