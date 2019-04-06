package org.hackathon.exception;

public class EventMembershipNotFoundException extends RuntimeException{
    public EventMembershipNotFoundException(String message) {
        super(message);
    }
}
