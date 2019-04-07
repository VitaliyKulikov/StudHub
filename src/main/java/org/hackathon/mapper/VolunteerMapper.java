package org.hackathon.mapper;

import org.hackathon.dto.VolunteerDto;
import org.hackathon.dto.VolunteerSignupDto;
import org.hackathon.entity.Volunteer;
import org.springframework.stereotype.Component;

@Component
public class VolunteerMapper {

    public Volunteer toEntity(VolunteerSignupDto dto) {

        Volunteer volunteer = new Volunteer();
        volunteer.setFirstName(dto.getFirstName());
        volunteer.setLastName(dto.getLastName());
        volunteer.setBirthDate(dto.getBirthDate());
        volunteer.setAddress(dto.getAddress());

        return volunteer;
    }

    public VolunteerDto toDto(Volunteer entity) {

        VolunteerDto volunteer = new VolunteerDto();
        volunteer.setFirstName(entity.getFirstName());
        volunteer.setLastName(entity.getLastName());
        volunteer.setId(entity.getId());

        return volunteer;
    }
}
