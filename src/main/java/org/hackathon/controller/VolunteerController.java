package org.hackathon.controller;

import org.hackathon.dto.EventMembershipDto;
import org.hackathon.entity.Principal;
import org.hackathon.service.EventMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Controller
public class VolunteerController {
    private final EventMembershipService service;

    @Autowired
    public VolunteerController(EventMembershipService service) {
        this.service = service;
    }

    @Secured("ROLE_ORGANISATION")
    @PutMapping("/volunteer/{id}/rate")
    public void setRating(@RequestParam @Positive @NotNull long volunteerId, @RequestBody @Valid EventMembershipDto dto){
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.apply(dto, principal.getEmail(), volunteerId);
    }
}
