package org.hackathon.dto;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class LoginDto {
    @NotBlank
    @Size(max=100)
    private String login;

    @NotBlank
    @Size(min=6, max=100)
    private String password;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
