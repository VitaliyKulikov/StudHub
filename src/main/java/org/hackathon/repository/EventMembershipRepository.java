package org.hackathon.repository;

import org.hackathon.entity.EventMembership;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;

@RepositoryRestResource(collectionResourceRel = "membership", path = "membership")
public interface EventMembershipRepository  extends PagingAndSortingRepository<EventMembership, Long> {

    @Override
    @RestResource(exported = false)
    @Secured("ROLE_ORGANISATION")
    <S extends EventMembership> S save(S membership);
}
