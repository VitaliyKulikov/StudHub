package org.hackathon.repository;

import org.hackathon.entity.Organisation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organisation, Long> {

    @Override
    <S extends Organisation> S save(S entity);

    @Override
    <S extends Organisation> Iterable<S> saveAll(Iterable<S> entities);

    Iterable<Organisation> findByConfirmed(Boolean confirmed);
}
