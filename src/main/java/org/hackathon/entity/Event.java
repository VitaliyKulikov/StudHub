package org.hackathon.entity;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "start_date", unique = true, nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", unique = true, nullable = false)
    private LocalDate endDate;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @Column(name = "address", unique = true, nullable = false)
    private String location;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer", nullable = false)
    private Principal issuer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Principal getIssuer() {
        return issuer;
    }

    public void setIssuer(Principal issuer) {
        this.issuer = issuer;
    }
}
