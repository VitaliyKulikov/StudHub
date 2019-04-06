package org.hackathon.controller;

import org.hackathon.PrincipalMapper;
import org.hackathon.config.SecurityProperties;
import org.hackathon.dto.LoginDto;
import org.hackathon.dto.PrincipalSignupDto;
import org.hackathon.repository.PrincipalRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {
    private final PrincipalService service;
    private final PrincipalRepository repository;
    private final AuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;
    private final JWTTokenProvider jwtTokenUtil;
    private final PrincipalMapper principalMapper;

    @Autowired
    public AuthController(PrincipalService service, PrincipalRepository repository, AuthenticationManager authenticationManager, SecurityProperties securityProperties, JWTTokenProvider jwtTokenUtil, PrincipalMapper principalMapper) {
        this.service = service;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.securityProperties = securityProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.principalMapper = principalMapper;
    }

    @PostMapping("/user-signup")
    public ResponseEntity registerUser(@RequestBody PrincipalSignupDto user) {
        repository.save(principalMapper.toEntity(user));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/organisation-signup")
    public ResponseEntity registerOrganisation(@RequestBody PrincipalSignupDto user) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token")
    public ResponseEntity<String> login(@RequestBody LoginDto loginUser) throws AuthenticationException {

        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        final String token = jwtTokenUtil.generateToken(auth);
        return ResponseEntity.ok(token);
    }
}
