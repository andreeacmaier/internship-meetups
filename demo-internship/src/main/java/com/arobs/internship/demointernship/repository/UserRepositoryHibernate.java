package com.arobs.internship.demointernship.repository;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.repository.interfaces.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepositoryHibernate implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryHibernate.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("  ==> UserRepositoryHibernate = findAll() ");
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public List<Proposal> findProposalsForUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Proposal> criteriaQuery = criteriaBuilder.createQuery(Proposal.class);
        Root<Proposal> root = criteriaQuery.from(Proposal.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), new User(userId)));
        Query query = session.createQuery(criteriaQuery);
        List<Proposal> queryResult = query.getResultList();
        LOGGER.info("ID from db: " + String.valueOf(queryResult.get(0).getUser().getId()));
        return queryResult;
    }
}
