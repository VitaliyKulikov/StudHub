package org.hackathon.repository;

import org.hackathon.entity.Principal;
import org.hackathon.mapper.PrincipalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface PrincipalRepository extends PagingAndSortingRepository<Principal, Integer> {
}
