package org.hackathon.repository;

import org.hackathon.entity.Principal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface PrincipalRepository extends PagingAndSortingRepository<Principal, Integer> {
}
