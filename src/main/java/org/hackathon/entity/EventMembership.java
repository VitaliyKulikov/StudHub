package org.hackathon.entity;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class EventMembership {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    private Volunteer volunteer;

    @ManyToOne
    private Event event;

    @RestResource(exported = false)
    @Column(name = "mark")
    @Max(5)
    @Min(0)
    private Integer mark;

    @RestResource(exported = false)
    private boolean participationConfirmed = false;

    @RestResource(exported = false)
    private int timeSpent;

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isParticipationConfirmed() {
        return participationConfirmed;
    }

    public void setParticipationConfirmed(boolean participationConfirmed) {
        this.participationConfirmed = participationConfirmed;
    }
}
