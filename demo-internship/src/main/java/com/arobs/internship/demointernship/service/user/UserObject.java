package com.arobs.internship.demointernship.service.user;

import com.arobs.internship.demointernship.entity.AwardingHistory;
import com.arobs.internship.demointernship.entity.Prize;
import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.AwardingHistoryRepository;
import com.arobs.internship.demointernship.repository.EventRepository;
import com.arobs.internship.demointernship.repository.PrizeRepository;
import com.arobs.internship.demointernship.repository.factory.ProposalRepositoryFactory;
import com.arobs.internship.demointernship.repository.factory.UserRepositoryFactory;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import com.arobs.internship.demointernship.service.prize.PrizeMapper;
import com.arobs.internship.demointernship.service.proposal.ProposalDTO;
import com.arobs.internship.demointernship.utils.AchievementConstants;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepositoryFactory userRepositoryFactory;

    @Autowired
    ProposalRepositoryFactory proposalRepositoryFactory;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    AwardingHistoryRepository awardingHistoryRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PrizeMapper prizeMapper;

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
        voteProposal(userRepository, userId, proposalId);
        giveUserPointsForVoting(userRepository, userId);
    }

    public boolean userVotedProposal(int userId, int proposalId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Proposal proposal = proposalRepository.findById(proposalId);
        List<Proposal> votedProposals = userRepository.findVotedProposalsForUser(userId);
        return votedProposals.contains(proposal);
    }

    private void voteProposal(UserRepository userRepository, int userId, int proposalId) {
        ProposalRepository proposalRepository = proposalRepositoryFactory.createProposalRepository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Proposal proposal = proposalRepository.findById(proposalId);
        if (userId != proposal.getUser().getId()) {
            LOGGER.info(" Proposal id = " + proposal.getId());
            userRepository.voteProposal(userId, proposal);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User can't vote his proposal.");
    }

    private void giveUserPointsForVoting(UserRepository userRepository, int userId) {
        User user = userRepository.findUserById(userId);
        userRepository.addAchievementPoints(AchievementConstants.PROPOSAL_VOTING_POINTS, userId);
    }


    public List<UserDTO> getUserTop() {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        List<User> users = userRepository.findAllOrderedByPoints();
        List<UserDTO> userDtos = userMapper.mapAsList(users, UserDTO.class);
        return userDtos;
    }

    public void givePrize(int prizeId) {
        UserRepository userRepository = userRepositoryFactory.createUserRespository(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE);
        Prize prize = prizeRepository.findById(prizeId);
        User userLeader = userRepository.findAllOrderedByPoints().get(0);

        AwardingHistory awardingHistory = new AwardingHistory();
        awardingHistory.setUser(userLeader);
        awardingHistory.setPrize(prize);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());

        awardingHistory.setAwardingDate(date);
        awardingHistory.setPoints(userLeader.getPoints());

        awardingHistoryRepository.save(awardingHistory);
        resetPoints(userRepository);
    }

    private void resetPoints(UserRepository userRepository) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setPoints(0);
            userRepository.updateUser(user);
        }
    }
}
