package com.arobs.internship.demointernship.repository.interfaces;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;

import java.util.List;

public interface UserRepository {

    User findUserById(int id);

    List<User> findAll();

    void save(User user);

    List<Proposal> findProposalsForUser(int userId);

    List<Proposal> findVotedProposalsForUser(int id);

    void voteProposal(int userId, Proposal proposal);

    void addAchievementPoints(int proposalVotingPoints, int userId);
}
