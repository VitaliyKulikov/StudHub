package org.hackathon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class EventMembership {
    @OneToMany
    @Column(name = "volunteer", nullable = false)
    private Principal volunteer;

    @OneToMany
    @Column(name = "validator", nullable = false)
    private Principal validator;

    @OneToMany
    @Column(name = "owner_organisation", nullable = false)
    private Organisation owner;

    @OneToMany
    @Column(name = "event", nullable = false)
    private Event event;

    @Column(name = "confirmed")
    private boolean confirmed = false;

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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
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
