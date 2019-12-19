package com.arobs.internship.demointernship.service.user;

import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
    @Transactional
    public List<UserDTO> getAllUsers() {
        return userObject.getAllUsers();
    }

    @Override
    @Transactional
    public boolean createUser(UserDTO userDTO) {
        return userObject.createUser(userDTO);
    }

    @Override
    @Transactional
    public List<ProposalDTO> getProposalsForUser(int userId) {
        return userObject.getCreatedProposals(userId);
    }
}
