package com.s1141775.iprwc_g2_backend.model;

import jakarta.persistence.*;

@Entity
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;
    @Column(name = "email")
    private String email;
    @Column(name = "type")
    private AccountType type;

    public Account(String username, String password, String salt, String email, AccountType type) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.type = type;
    }

    public Account() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}

