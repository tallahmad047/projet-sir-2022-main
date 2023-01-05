package com.ca.formation.formationdemo1.dto;


import com.ca.formation.formationdemo1.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UtilisateurDto  {


    private Long id;

    private boolean enabled = true;
    private String username;
    private String password;
    private String name;



    public UtilisateurDto() {
    }

    public UtilisateurDto(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;

    }

    public UtilisateurDto(String username, String password) {

            this.username = username;
            this.password = password;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {

        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
