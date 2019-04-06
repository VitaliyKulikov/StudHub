package org.hackathon.controller;

import org.hackathon.entity.Principal;
import org.hackathon.service.EventMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Controller
public class EventController {

    private final EventMembershipService service;

    @Autowired
    public EventController(EventMembershipService service) {
        this.service = service;
    }

    @PutMapping("/api/events/{eventId}/apply")
    public void registerForEvent(@RequestParam @Positive @NotNull long eventId) {
        String email = ((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        service.addEventMember(eventId, email);
    }
}
