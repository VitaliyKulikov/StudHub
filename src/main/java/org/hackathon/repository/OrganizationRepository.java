package org.hackathon.repository;

import org.hackathon.entity.Event;
import org.hackathon.entity.Organisation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "organizations", path = "organizations")
public interface OrganizationRepository extends PagingAndSortingRepository<Organisation, Long> {


    @Override
    @RestResource(exported = false)
    <S extends Organisation> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Organisation> Iterable<S> saveAll(Iterable<S> entities);
}
