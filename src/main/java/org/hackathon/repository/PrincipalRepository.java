package org.hackathon.repository;

import org.hackathon.entity.Principal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrincipalRepository extends CrudRepository<Principal, Long> {

    <T extends Principal> Optional<T> findByEmail(String email);
}
