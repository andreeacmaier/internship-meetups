package com.arobs.internship.demointernship.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prize")
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prize_seq")
    @Column(name = "prize_id", nullable = false)
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "value", nullable = false)
    private String value;

    @OneToMany(
            mappedBy = "prize",
            cascade = CascadeType.ALL
    )
    Set<AwardingHistory> awards = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<AwardingHistory> getAwards() {
        return awards;
    }

    public void setAwards(Set<AwardingHistory> awards) {
        this.awards = awards;
    }
}
