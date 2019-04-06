package org.hackathon.entity;

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
    private Organisation owner;

    @ManyToOne
    private Event event;

    @Column(name = "mark")
    @Max(5)
    @Min(0)
    @NotNull
    private Integer mark;

    @Column(name = "timeSpent")
    private Integer timeSpent;

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Organisation getOwner() {
        return owner;
    }

    public void setOwner(Organisation owner) {
        this.owner = owner;
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
}
