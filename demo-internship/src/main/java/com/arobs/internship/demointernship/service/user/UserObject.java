package com.arobs.internship.demointernship.service.user;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.factory.ProposalRepositoryFactory;
import com.arobs.internship.demointernship.repository.factory.UserRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepositoryFactory userRepositoryFactory;

    @Autowired
    ProposalRepositoryFactory proposalRepositoryFactory;

    @Autowired
    UserMapper userMapper;

    public UserDTO getUserById(int id) throws ClassNotFoundException {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.JDBC_REPOSITORY_TYPE);
        User user = userRepository.findUserById(id);
        if (user != null) {
            return userMapper.map(user, UserDTO.class);
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        List<User> users = userRepository.findAll();
        if (users != null) {
            return userMapper.mapAsList(users, UserDTO.class);
        }
        return null;
    }

    public void createUser(UserDTO userDTO) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        User user = userMapper.map(userDTO, User.class);
        userRepository.save(user);
    }

    public void saveUser(UserDTO userDTO) {
        User user = userMapper.map(userDTO, User.class);
    }

    public List<ProposalDTO> getCreatedProposals(int userId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        List<Proposal> proposalList = userRepository.findProposalsForUser(userId);
        List<ProposalDTO> proposalDTOList = mapFromEntityToDto(proposalList);
        LOGGER.info("ID from object : " + String.valueOf(proposalDTOList.get(0).getUserId()));
        return proposalDTOList;
    }

    public List<ProposalDTO> getVotedProposalsForUser(int id) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        List<Proposal> proposalList = userRepository.findVotedProposalsForUser(id);
        List<ProposalDTO> proposalDTOList = mapFromEntityToDto(proposalList);
        return proposalDTOList;
    }

    private List<ProposalDTO> mapFromEntityToDto(List<Proposal> proposalList) {
        List<ProposalDTO> proposalDTOList = new ArrayList<>();
        for (Proposal proposal : proposalList) {
            ProposalDTO proposalDTO = new ProposalDTO();
            proposalDTO.setUserId(proposal.getUser().getId());
            proposalDTO.setDescription(proposal.getDescription());
            proposalDTO.setTitle(proposal.getTitle());
            proposalDTO.setType(proposal.getType());
            proposalDTO.setDifficulty(proposal.getDifficulty());
            proposalDTO.setLanguage(proposal.getLanguage());
            proposalDTO.setDurationInMinutes(proposal.getDurationInMinutes());
            proposalDTO.setMaximumPeople(proposal.getMaximumPeople());
            proposalDTOList.add(proposalDTO);
        }
        return proposalDTOList;
    }


    public void vote(int userId, int proposalId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Proposal proposal = proposalRepository.findById(proposalId);
        LOGGER.info(" Proposal id = " + proposal.getId());
        userRepository.voteProposal(userId, proposal);
    }

    public boolean userVotedProposal(int userId, int proposalId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Proposal proposal = proposalRepository.findById(proposalId);
        List<Proposal> votedProposals = userRepository.findVotedProposalsForUser(userId);
        return votedProposals.contains(proposal);
    }
}
