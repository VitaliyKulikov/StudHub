package org.hackathon.controller;

import org.hackathon.entity.Principal;
import org.hackathon.mapper.PrincipalMapper;
import org.hackathon.mapper.VolunteerMapper;
import org.hackathon.config.SecurityProperties;
import org.hackathon.dto.LoginDto;
import org.hackathon.dto.VolunteerSignupDto;
import org.hackathon.entity.Volunteer;
import org.hackathon.repository.OrganizationRepository;
import org.hackathon.repository.PrincipalRepository;
import org.hackathon.repository.VolunteerRepository;
import org.hackathon.security.JWTTokenProvider;
import org.hackathon.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
public class AuthController {
    private final PrincipalService service;
    private final VolunteerRepository volunteerRepository;
    private final OrganizationRepository orgRepository;
    private final PrincipalRepository principalRepository;
    private final AuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;
    private final JWTTokenProvider jwtTokenUtil;
    private final VolunteerMapper volunteerMapper;
    private final PrincipalMapper principalMapper;

    @Autowired
    public AuthController(PrincipalService service, VolunteerRepository volunteerRepository, OrganizationRepository orgRepository, PrincipalRepository principalRepository, AuthenticationManager authenticationManager, SecurityProperties securityProperties, JWTTokenProvider jwtTokenUtil, VolunteerMapper volunteerMapper, PrincipalMapper principalMapper) {
        this.service = service;
        this.volunteerRepository = volunteerRepository;
        this.orgRepository = orgRepository;
        this.principalRepository = principalRepository;
        this.authenticationManager = authenticationManager;
        this.securityProperties = securityProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.volunteerMapper = volunteerMapper;
        this.principalMapper = principalMapper;
    }

    @PostMapping("/api/user-signup")
    @Transactional
    public ResponseEntity registerUser(@RequestBody @Valid VolunteerSignupDto user) {

        if (!user.getPassword1().equals(user.getPassword2())) {
            throw new ValidationException("Passwords must match");
        }

        Volunteer volunteer = volunteerMapper.toEntity(user);
        Principal principal = principalMapper.toEntity(user);
        principalRepository.save(principal);
        volunteerRepository.save(volunteer);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/organisation-signup")
    public ResponseEntity registerOrganisation(@RequestBody @Valid VolunteerSignupDto user) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/token")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginUser) throws AuthenticationException {

        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        final String token = jwtTokenUtil.generateToken(auth);
        return ResponseEntity.ok('"' + token + '"');
    }
}
