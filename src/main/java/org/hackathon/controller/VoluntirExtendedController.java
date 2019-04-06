package org.hackathon.controller;

import org.hackathon.entity.EventMembership;
import org.hackathon.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class VoluntirExtendedController {

    private VolunteerRepository volunteerRepository;

    @Autowired
    public VoluntirExtendedController(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @GetMapping("/api/volunteers/{id}/rating")
    public ResponseEntity<Double> getRating(@PathVariable("id") Long id){

        Double average = volunteerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new)
                .getEventMemberships().stream()
                .mapToInt(EventMembership::getMark)
                .filter(Objects::nonNull)
                .summaryStatistics().getAverage();

        return ResponseEntity.ok(average);
    }
}
