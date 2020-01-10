package com.arobs.internship.demointernship.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "points")
    private int points;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "vote",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "proposal_id")}
    )
    Set<Proposal> votedProposals = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    Set<Attendance> eventsAttended = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    Set<Proposal> proposalsCreated = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    Set<Event> eventsCreated = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    Set<AwardingHistory> awards = new HashSet<>();

    public User() {}

    public User(int userId) {
        this.id = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Set<Proposal> getVotedProposals() {
        return votedProposals;
    }

    public void setVotedProposals(Set<Proposal> votedProposals) {
        this.votedProposals = votedProposals;
    }

    public Set<Attendance> getEventsAttended() {
        return eventsAttended;
    }

    public void setEventsAttended(Set<Attendance> eventsAttended) {
        this.eventsAttended = eventsAttended;
    }

    public Set<Proposal> getProposalsCreated() {
        return proposalsCreated;
    }

    public void setProposalsCreated(Set<Proposal> proposalsCreated) {
        this.proposalsCreated = proposalsCreated;
    }

    public Set<Event> getEventsCreated() {
        return eventsCreated;
    }

    public void setEventsCreated(Set<Event> eventsCreated) {
        this.eventsCreated = eventsCreated;
    }

    public Set<AwardingHistory> getAwards() {
        return awards;
    }

    public void setAwards(Set<AwardingHistory> awards) {
        this.awards = awards;
    }
}
