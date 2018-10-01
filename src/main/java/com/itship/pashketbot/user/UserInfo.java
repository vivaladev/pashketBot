package com.itship.pashketbot.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfo {

    public UserInfo(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public UserInfo(String name, String password, String role, String lang) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.lang = lang;
    }

    @JsonProperty(required = true)
    private String name;

    @JsonIgnore
    private String password;

    private String role;

    private String lang;

    private Boolean initial;

    private String mail;

}
