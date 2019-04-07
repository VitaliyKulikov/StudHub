package org.hackathon.mapper;

import org.hackathon.dto.OrganisationSignupDto;
import org.hackathon.entity.Organisation;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public Organisation toEntity(OrganisationSignupDto dto) {

        Organisation volunteer = new Organisation();
        volunteer.setFirstName(dto.getFirstName());
        volunteer.setLastName(dto.getLastName());
        volunteer.setImage(dto.getImage());
        volunteer.setName(dto.getFirstName());
        volunteer.setUrl(dto.getWebpageReference());
        return volunteer;
    }
}
