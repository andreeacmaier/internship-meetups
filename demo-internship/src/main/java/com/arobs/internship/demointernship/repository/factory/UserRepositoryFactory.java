package com.arobs.internship.demointernship.repository.factory;

import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import com.arobs.internship.demointernship.repository.UserRepositoryHibernate;
import com.arobs.internship.demointernship.repository.UserRepositoryJDBCImpl;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryFactory {

    @Autowired
    UserRepositoryJDBCImpl userRepositoryJDBC;

    @Autowired
    UserRepositoryHibernate userRepositoryHibernate;

    public UserRepository createUserRespository(String type) {
        if (type.equals(RepositoryConstants.JDBC_REPOSITORY_TYPE)) {
            return userRepositoryJDBC;
        } else if (type.equals(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE)) {
            return userRepositoryHibernate;
        }
        return null;
    }
}
