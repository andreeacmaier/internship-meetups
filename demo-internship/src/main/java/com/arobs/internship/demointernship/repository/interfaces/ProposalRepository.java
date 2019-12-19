package com.arobs.internship.demointernship.repository.interfaces;

import com.arobs.internship.demointernship.entity.Proposal;

public interface ProposalRepository {

    void save(Proposal proposal);

    Proposal findById(int id);

}