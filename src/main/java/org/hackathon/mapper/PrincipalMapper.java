package org.hackathon.mapper;

import org.hackathon.dto.SignupDto;
import org.hackathon.entity.Principal;
import org.hackathon.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalMapper {
    private BCryptPasswordEncoder encoder;

    @Autowired
    public PrincipalMapper(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Principal toEntity(SignupDto dto){
        Principal principal = new Principal();
        principal.setEmail(dto.getEmail());
        principal.setPassword(encoder.encode(dto.getPassword()));
        principal.setRole(Role.VOLUNTEER);
        return principal;
    }
}
