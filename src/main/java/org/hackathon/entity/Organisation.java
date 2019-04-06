package org.hackathon.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Organisation extends Principal{

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 1500)
    private String description;

    @Column(name = "image")
    private byte[] image;

    @ManyToMany
    @Column(name = "members")
    private List<Volunteer> members;

    public Organisation() {
    }

    public Organisation(int id, String name, String description, byte[] image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
