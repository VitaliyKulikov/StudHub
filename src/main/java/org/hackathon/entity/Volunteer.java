package org.hackathon.entity;

import org.hackathon.security.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Volunteer extends Principal {

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    private byte[] image;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 300)
    private String address;

    @ManyToMany
    private List<Organisation> organisations;

}
