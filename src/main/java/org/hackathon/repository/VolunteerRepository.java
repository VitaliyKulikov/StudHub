package org.hackathon.repository;

import org.hackathon.entity.Principal;
import org.hackathon.entity.Volunteer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "volunteers", path = "volunteers")
public interface VolunteerRepository extends PagingAndSortingRepository<Volunteer, Long> {

    // Prevents POST /people and PATCH /people/:id
    @Override
    @RestResource(exported = false)
    <S extends Volunteer> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Volunteer> Iterable<S> saveAll(Iterable<S> entities);
}
