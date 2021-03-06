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
    public void createUser(UserDTO userDTO) {
        userObject.createUser(userDTO);
    }

    @Override
    @Transactional
    public List<ProposalDTO> getProposalsForUser(int userId) {
        return userObject.getCreatedProposals(userId);
    }

    @Override
    @Transactional
    public List<ProposalDTO> getVotedProposalsForUser(int id) {
        return userObject.getVotedProposalsForUser(id);
    }

    @Override
    @Transactional
    public void vote(int userId, int proposalId) {
        userObject.vote(userId, proposalId);
    }

    @Override
    @Transactional
    public boolean userVotedProposal(int userId, int proposalId) {
        return userObject.userVotedProposal(userId, proposalId);
    }

    @Override
    @Transactional
    public List<UserDTO> getUserTop() {
        return userObject.getUserTop();
    }

    @Override
    @Transactional
    public void givePrize(int prizeId) {
        userObject.givePrize(prizeId);
    }
}
