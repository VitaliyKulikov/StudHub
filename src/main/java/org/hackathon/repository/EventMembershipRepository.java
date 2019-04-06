package org.hackathon.repository;

import org.hackathon.entity.EventMembership;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "membership", path = "membership")
public interface EventMembershipRepository extends PagingAndSortingRepository<EventMembership, Long> {

    @Secured("ROLE_ORGANISATION")
    @Query("UPDATE EventMembership em SET participationConfirmed=true WHERE em.id=:id")
    void confirmMembership(@Param("id") long eventMembership);

    @RestResource(exported = false)
    Optional<EventMembership> findByVolunteerIdAndAndEventId(long volunteer, long event);

}
