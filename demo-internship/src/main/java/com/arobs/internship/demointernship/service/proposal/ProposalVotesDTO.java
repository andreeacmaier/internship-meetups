package com.arobs.internship.demointernship.service.proposal;

public class ProposalVotesDTO {

    private String title;
    private int votesNumber;

    public ProposalVotesDTO(String title, int votesNumber) {
        this.title = title;
        this.votesNumber = votesNumber;
    }

    public ProposalVotesDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVotesNumber() {
        return votesNumber;
    }

    public void setVotesNumber(int votesNumber) {
        this.votesNumber = votesNumber;
    }
}
