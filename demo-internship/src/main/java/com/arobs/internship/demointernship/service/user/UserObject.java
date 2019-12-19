package com.arobs.internship.demointernship.service.user;

import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import com.arobs.internship.demointernship.repository.factory.UserRepositoryFactory;
import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl .class);

    @Autowired
    UserRepositoryFactory userRepositoryFactory;

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
        if (users!=null){
            return userMapper.mapAsList(users, UserDTO.class);
        }
        return null;
    }

    public boolean createUser(UserDTO userDTO) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.JDBC_REPOSITORY_TYPE);
        User user = userMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    public void saveUser(UserDTO userDTO) {
        User user = userMapper.map(userDTO, User.class);
    }

    public List<ProposalDTO> getCreatedProposals(int userId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        List<ProposalDTO> proposalDTOList = userMapper.mapAsList(userRepository.findProposalsForUser(userId), ProposalDTO.class);
        LOGGER.info("ID from object : " + String.valueOf(proposalDTOList.get(0).getUserId()));
        return proposalDTOList;
    }
}
