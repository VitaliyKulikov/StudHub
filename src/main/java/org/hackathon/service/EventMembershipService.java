package org.hackathon.service;

import org.hackathon.dto.EventMembershipDto;
import org.hackathon.entity.Event;
import org.hackathon.entity.EventMembership;
import org.hackathon.entity.Volunteer;
import org.hackathon.exception.EventMembershipNotFoundException;
import org.hackathon.exception.ResourceNotFoundException;
import org.hackathon.repository.EventMembershipRepository;
import org.hackathon.repository.EventRepository;
import org.hackathon.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EventMembershipService {
    private final EventMembershipRepository membershipRepository;
    private final EventRepository eventRepository;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public EventMembershipService(EventMembershipRepository membershipRepository, EventRepository eventRepository, VolunteerRepository volunteerRepository) {
        this.membershipRepository = membershipRepository;
        this.eventRepository = eventRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Transactional
    public void apply(EventMembershipDto dto, String orgEmail, long volunteerId) {
        Optional<EventMembership> eventMembership = membershipRepository.findByVolunteerIdAndAndEventId(volunteerId, dto.getEventId());
        EventMembership membership =
                eventMembership
                        .filter(e -> e.getEvent().getOwner().getPrincipal().getEmail().equals(orgEmail))
                        .orElseThrow(() ->
                                new EventMembershipNotFoundException("Selected volunteer doesn't belong to selected event or this event doesn't belong to this organisation."));

        membership.setMark(dto.getRate());
        membership.setTimeSpent(dto.getTime());
        membershipRepository.save(membership);
    }

    @Transactional
    public void addEventMember(Long eventId, String email) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Selected event does not exist."));
        Volunteer volunteer = volunteerRepository.findByPrincipalEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Selected volunteer does not exist."));
        EventMembership eventMembership = new EventMembership();
        eventMembership.setEvent(event);
        eventMembership.setVolunteer(volunteer);
        eventMembership.setParticipationConfirmed(false);
        membershipRepository.save(eventMembership);
    }

    public Event findEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event does not exist."));
    }

}
