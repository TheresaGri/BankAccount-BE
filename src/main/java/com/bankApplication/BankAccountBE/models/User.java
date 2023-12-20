package com.bankApplication.BankAccountBE.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities;

    public User(String username, String password, Set<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
