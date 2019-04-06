package org.hackathon.mapper;

import org.hackathon.entity.Principal;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PrincipalMapper implements RowMapper<Principal> {
    @Nullable
    @Override
    public Principal mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Principal(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"));
    }
}
