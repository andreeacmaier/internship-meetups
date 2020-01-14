package com.arobs.internship.demointernship.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id", nullable = false)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "duration_min", nullable = false)
    private int durationInMinutes;

    @Column(name = "max_people", nullable = false)
    private int maximumPeople;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "event_date", nullable = false)
    private String date;

    @Column(name = "room", nullable = false)
    private int room;

    @Column(name = "status", columnDefinition = "false")
    private boolean status;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL
    )
    Set<Attendance> attendees = new HashSet<>();

    public Event(Proposal proposal, int room, String date){
        this.user = proposal.getUser();
        this.title = proposal.getTitle();
        this.description = proposal.getDescription();
        this.difficulty = proposal.getDifficulty();
        this.language = proposal.getLanguage();
        this.durationInMinutes = proposal.getDurationInMinutes();
        this.maximumPeople = proposal.getMaximumPeople();
        this.room = room;
        this.date = date;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getMaximumPeople() {
        return maximumPeople;
    }

    public void setMaximumPeople(int maximumPeople) {
        this.maximumPeople = maximumPeople;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Attendance> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Attendance> attendees) {
        this.attendees = attendees;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
