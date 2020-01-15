package com.arobs.internship.demointernship.service.user;

import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO getUserById(int id) throws ClassNotFoundException;

    List<UserDTO> getAllUsers();

    void createUser(UserDTO userDTO);

    List<ProposalDTO> getProposalsForUser(int id);

    List<ProposalDTO> getVotedProposalsForUser(int id);

    void vote(int userId, int proposalId);

    boolean userVotedProposal(int userId, int proposalId);

    List<UserDTO> getUserTop();

    void givePrize(int prizeId);
}
