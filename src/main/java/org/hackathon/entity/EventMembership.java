package org.hackathon.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class EventMembership {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @ManyToOne
    private Principal volunteer;

    @ManyToOne
    private Principal validator;

    @ManyToOne
    private Organisation owner;

    @ManyToOne
    private Event event;

    @Column(name = "mark")
    private int mark;

    @Column(name = "timeSpent")
    private int timeSpent;

    public Principal getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Principal volunteer) {
        this.volunteer = volunteer;
    }

    public Principal getValidator() {
        return validator;
    }

    public void setValidator(Principal validator) {
        this.validator = validator;
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
}
