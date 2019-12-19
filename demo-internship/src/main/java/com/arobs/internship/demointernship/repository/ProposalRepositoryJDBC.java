package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.configuration.Datasource;
import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ProposalRepositoryJDBC implements ProposalRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    Datasource datasource;

    public ProposalRepositoryJDBC() {
    }

    public void save(Proposal proposal) {

        String querry = "INSERT INTO proposal(user_id, title, description, type, difficulty," +
                "language, votes, duration_min, max_people) VALUES (?,?,?,?,?,?,?,?)";
        try (
                Connection connection = datasource.customDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(querry);
        ) {
            preparedStatement.setInt(1, proposal.getUser().getId());
            preparedStatement.setString(2, proposal.getTitle());
            preparedStatement.setString(3, proposal.getDescription());
            preparedStatement.setString(4, proposal.getType());
            preparedStatement.setString(5, proposal.getDifficulty());
            preparedStatement.setString(6, proposal.getLanguage());
            preparedStatement.setInt(7, proposal.getDurationInMinutes());
            preparedStatement.setInt(8, proposal.getMaximumPeople());

            int inserted = preparedStatement.executeUpdate();
            LOGGER.info("INSERTED = " + inserted);
            /*if (inserted == 1) {
                return inserted;
            }*/

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Proposal findById(int id) {
        return null;
    }
}