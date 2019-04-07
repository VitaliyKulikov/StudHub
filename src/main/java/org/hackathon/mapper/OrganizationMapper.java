package org.hackathon.mapper;

import org.hackathon.dto.OrganisationSignupDto;
import org.hackathon.entity.Organisation;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public Organisation toEntity(OrganisationSignupDto dto) {

        Organisation org = new Organisation();
        org.setFirstName(dto.getFirstName());
        org.setLastName(dto.getLastName());
        org.setImage(dto.getImage());
        org.setName(dto.getOrgName());
        org.setUrl(dto.getWebpageReference());
        return org;
    }
}
