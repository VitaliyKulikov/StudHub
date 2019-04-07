package org.hackathon.controller;

import org.hackathon.dto.EventDto;
import org.hackathon.dto.NotificationDto;
import org.hackathon.entity.Event;
import org.hackathon.entity.Principal;
import org.hackathon.entity.Volunteer;
import org.hackathon.mail.MailBox;
import org.hackathon.mapper.EventMapper;
import org.hackathon.repository.EventRepository;
import org.hackathon.service.EventMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/api/events")
public class EventController {

    private final EventMembershipService service;
    private final MailBox mailBox;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventController(EventMembershipService service, MailBox mailBox, EventRepository eventRepository, EventMapper eventMapper) {
        this.service = service;
        this.mailBox = mailBox;
        this.eventRepository = eventRepository;
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

    //For testing purposes
    @PutMapping("/notificationsss")
    public void broadcastMessageToEventMembers(@Valid @RequestBody NotificationDto notification) {

        List<String> participants = Arrays.asList("lalaith.ned.midh@gmail.com");

        mailBox.sendMail(participants, notification.getTitle(),
                "Нове повідомлення:\n" + notification.getDescription());
    }
}
