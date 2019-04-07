package org.hackathon.controller;

import org.hackathon.dto.EventMembershipDto;
import org.hackathon.entity.EventMembership;
import org.hackathon.entity.Principal;
import org.hackathon.repository.VolunteerRepository;
import org.hackathon.service.EventMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

@RestController
public class VolunteerExtendedController {

    private VolunteerRepository volunteerRepository;
    private final EventMembershipService service;
    @Autowired
    public VolunteerExtendedController(VolunteerRepository volunteerRepository, EventMembershipService service) {
        this.volunteerRepository = volunteerRepository;
        this.service = service;
    }

    @GetMapping("/api/volunteers/{id}/rate")
    public ResponseEntity<Double> getRating(@PathVariable("id") Long id){

        List<EventMembership> allMemberships = volunteerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new)
                .getEventMemberships();

        Double averageRating = allMemberships.stream()
                .mapToInt(EventMembership::getMark)
                .filter(Objects::nonNull)
                .summaryStatistics().getAverage();

        return ResponseEntity.ok(averageRating);
    }

    @Secured("ROLE_ORGANISATION")
    @PutMapping("/volunteer/{id}/rate")
    public void setRating(@RequestParam @Positive @NotNull long volunteerId, @RequestBody @Valid EventMembershipDto dto){
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.apply(dto, principal.getEmail(), volunteerId);
    }
}
