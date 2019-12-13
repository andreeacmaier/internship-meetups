package com.arobs.internship.demointernship.service.user;

import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl .class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserDTO getUserById(int id) throws ClassNotFoundException {
        LOGGER.info("LOG UserServiceImpl = getUserById");
        User user = userRepository.findUserById(id);
        if (user != null) {
            return userMapper.map(user, UserDTO.class);
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users!=null){
            return userMapper.mapAsList(users, UserDTO.class);
        }
        return null;
    }
}
