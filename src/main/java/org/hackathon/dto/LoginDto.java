package org.hackathon.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class LoginDto {
    @NotBlank
    @Size(max=100)
    private String email;

    @NotBlank
    @Size(min=6, max=100)
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
