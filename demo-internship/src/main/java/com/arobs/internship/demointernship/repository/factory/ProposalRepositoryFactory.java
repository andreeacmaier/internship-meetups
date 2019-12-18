package com.arobs.internship.demointernship.repository.factory;

import com.arobs.internship.demointernship.repository.ProposalRepositoryHibernate;
import com.arobs.internship.demointernship.repository.ProposalRepositoryJDBC;
import com.arobs.internship.demointernship.repository.interfaces.ProposalRepository;
import com.arobs.internship.demointernship.utils.RepositoryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalRepositoryFactory {

    @Autowired
    ProposalRepositoryJDBC proposalRepositoryJDBC;

    @Autowired
    ProposalRepositoryHibernate proposalRepositoryHibernate;

    public ProposalRepository createProposalRepository(String type) {
        if (type.equals(RepositoryConstants.JDBC_REPOSITORY_TYPE)) {
            return proposalRepositoryJDBC;
        } else if (type.equals(RepositoryConstants.HIBERNATE_REPOSITORY_TYPE)) {
            return proposalRepositoryHibernate;
        }

        return null;
    }
}
