package org.hackathon.controller;

import org.hackathon.entity.Organisation;
import org.hackathon.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationsController {


    OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationsController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @GetMapping()
    ResponseEntity<Resources<?>> getAllActiveOrganizations(@RequestParam(required = false) Boolean confirmed) {
        Iterable<Organisation> active = organizationRepository.findByConfirmed(confirmed == null ? true : confirmed);
        Resources<?> resources;
        if (!active.iterator().hasNext()) {
            EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
            EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(Organisation.class);
            List<Object> content = Collections.singletonList(wrapper);

            resources = new Resources<>(content);
        } else {
            resources = new Resources<>(active);
        }

        return ResponseEntity.ok(resources);
    }
}
