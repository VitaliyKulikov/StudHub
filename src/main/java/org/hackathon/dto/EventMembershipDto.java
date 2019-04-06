package org.hackathon.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class EventMembershipDto {

    @Positive
    @NotNull
    private Integer eventId;
    @PositiveOrZero
    @Max(5)
    @NotNull
    private Integer rate;
    @PositiveOrZero
    @NotNull
    private Integer time;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
