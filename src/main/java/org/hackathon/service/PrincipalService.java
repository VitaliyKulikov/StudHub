package org.hackathon.service;

import org.hackathon.entity.Principal;
import org.hackathon.exception.UserNotFoundException;
import org.hackathon.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService implements UserDetailsService {
    private final PrincipalRepository principalRepository;

    @Autowired
    public PrincipalService(PrincipalRepository principalRepository) {
        this.principalRepository = principalRepository;
    }

    @Override
    public Principal loadUserByUsername(String email) throws UsernameNotFoundException {
        return principalRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " does not exist."));
    }

    public Principal loadUserById(long id) throws UsernameNotFoundException {
        return principalRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist."));
    }
}
