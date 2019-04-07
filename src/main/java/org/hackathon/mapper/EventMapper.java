package org.hackathon.mapper;

import org.hackathon.dto.EventDto;
import org.hackathon.dto.OrganisationDto;
import org.hackathon.entity.Event;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EventMapper {

    private VolunteerMapper volunteerMapper;

    public EventMapper(VolunteerMapper volunteerMapper) {
        this.volunteerMapper = volunteerMapper;
    }

    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();

        eventDto.setDescription(event.getDescription());
        eventDto.setEndDate(event.getEndDate());
        eventDto.setId(event.getId());
        eventDto.setImage(event.getImage());
        eventDto.setLocation(event.getLocation());
        eventDto.setName(event.getName());
        eventDto.setOwner(new OrganisationDto(event.getOwner().getId(), event.getOwner().getName()));

        eventDto.setParticipants(event.getParticipants().stream()
                .map(volunteerMapper::toDto)
                .collect(Collectors.toList())
        );

        return eventDto;
    }
}
