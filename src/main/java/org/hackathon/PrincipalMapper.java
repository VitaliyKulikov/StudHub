package org.hackathon;

import org.hackathon.dto.PrincipalSignupDto;
import org.hackathon.entity.Principal;
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

    public Principal toEntity(PrincipalSignupDto dto){
        Principal principal = new Principal();
        principal.setEmail(dto.getEmail());
        principal.setPassword(encoder.encode(dto.getPassword()));
        principal.setName(dto.getName());
        principal.setSurname(dto.getSurname());
        principal.setRole(dto.getRole());
        principal.setImage(dto.getImage());
        principal.setBirthDate(dto.getBirthDate());
        principal.setAddress(dto.getAddress());
        return principal;
    }
}
