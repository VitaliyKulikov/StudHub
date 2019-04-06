package org.hackathon.controller;

import org.hackathon.entity.Principal;
import org.hackathon.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

//@Controller
public class TestController {

    private PrincipalRepository repository;

    @Autowired
    public TestController(PrincipalRepository repository) {
        this.repository = repository;
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<Principal>> getUsers() {
//        return ResponseEntity.ok(new ArrayList(repository.findAll().iterator()));
//    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {

        return ResponseEntity.ok("Hey Ho!");
    }

    @GetMapping("/exception")
    public ResponseEntity<String> getException() {

        throw new IllegalArgumentException("Something went wrong");
    }
}
