package org.hackathon.controller;

import org.hackathon.dto.EventDto;
import org.hackathon.dto.NotificationDto;
import org.hackathon.entity.Event;
import org.hackathon.entity.Organisation;
import org.hackathon.entity.Principal;
import org.hackathon.entity.Volunteer;
import org.hackathon.exception.ResourceNotFoundException;
import org.hackathon.mail.MailBox;
import org.hackathon.mapper.EventMapper;
import org.hackathon.repository.EventRepository;
import org.hackathon.repository.OrganizationRepository;
import org.hackathon.service.EventMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@Controller
@RequestMapping("/api/events")
public class EventController {
    private static String UPLOADED_FOLDER = "C://temp//";

    private final EventMembershipService service;
    private final MailBox mailBox;
    private final EventRepository eventRepository;
    private final OrganizationRepository orgRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventController(EventMembershipService service, MailBox mailBox, EventRepository eventRepository, OrganizationRepository orgRepository, EventMapper eventMapper) {
        this.service = service;
        this.mailBox = mailBox;
        this.eventRepository = eventRepository;
        this.orgRepository = orgRepository;
        this.eventMapper = eventMapper;
    }

    @PutMapping("/{eventId}/apply")
    public void registerForEvent(@RequestParam @Positive @NotNull Long eventId) {
        String email = ((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        service.addEventMember(eventId, email);
    }

    @GetMapping
    ResponseEntity<Resources<?>> getAllEvents() {
        List<EventDto> events = StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ControllerHelper.getResource(events, Event.class));
    }

    @Secured("ROLE_ORGANISATION")
    @PutMapping("/{id}/notification")
    public void broadcastMessageToEventMembers(@PathParam("id") long id, @Valid @RequestBody NotificationDto notification) {
        Event event = service.findEvent(id);
        List<String> participants = event.getParticipants().stream()
                .map(Volunteer::getPrincipal)
                .map(Principal::getEmail)
                .collect(Collectors.toList());
        String orgName = event.getOwner().getName();

        mailBox.sendMail(participants, notification.getTitle(),
                "Нове повідомлення від " + orgName + ":\n" + notification.getDescription());
    }

    @PostMapping(consumes = "multipart/form-data")
    public void createEvent(@Valid @ModelAttribute EventDto eventDto) throws IOException {
        MultipartFile file = eventDto.getImage();
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Image cannot be empty.");
        }

        String email = ((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        byte[] bytes = file.getBytes();
        Organisation organisation = orgRepository.findByPrincipalEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Selected organisation does not exist."));

        Event event = eventMapper.fromDto(eventDto);
        event.setOwner(organisation);
        event.setImage(bytes);
        eventRepository.save(event);
    }
}
