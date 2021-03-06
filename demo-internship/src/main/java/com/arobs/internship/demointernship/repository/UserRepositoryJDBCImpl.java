package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.configuration.Datasource;
import com.arobs.internship.demointernship.entity.Event;
import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryJDBCImpl implements UserRepository {

    @Autowired
    Datasource datasource;

    public User findUserById(int id)  {
        String querry = "SELECT * FROM users WHERE user_id = " + id;
        try (Connection connection = datasource.customDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            if (resultSet.next()) {
                return extractItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String querry = "SELECT * FROM users";
        try (Connection connection = datasource.customDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)
        ) {
            while (resultSet.next()) {
                User user = extractItemFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User extractItemFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        user.setPoints(resultSet.getInt("points"));

        return user;
    }

    public void save(User user) {
        String querry = "INSERT INTO users(first_name, last_name, role, email, password, points) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection connection = datasource.customDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querry);) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getPoints());

            int inserted = preparedStatement.executeUpdate();

            if (inserted == 1) {
//                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return false;
    }

    @Override
    public List<Proposal> findProposalsForUser(int userId) {
        return null;
    }

    @Override
    public List<Proposal> findVotedProposalsForUser(int id) {
        return null;
    }

    @Override
    public void voteProposal(int userId, Proposal proposal) {
    }

    @Override
    public void addAchievementPoints(int proposalVotingPoints, int userId) {
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<User> findAllOrderedByPoints() {
        return null;
    }
}
