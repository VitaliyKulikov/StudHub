package org.hackathon.repository;

import org.hackathon.entity.Principal;
import org.hackathon.mapper.PrincipalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PrincipalRepository {
    private DataSource dataSource;
    private PrincipalMapper mapper;

    @Autowired
    public PrincipalRepository(DataSource dataSource, PrincipalMapper mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public List<Principal> getAllUsers(){
        return new JdbcTemplate(dataSource)
                .query("SELECT * FROM PRINCIPAL", mapper);

    }
}
