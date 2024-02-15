package com.s1141775.iprwc_g2_backend.model;

import jakarta.persistence.*;

@Entity
public class AccountJWTDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @OneToOne
    @JoinColumn(name = "account")
    private Account account;
    @Column(name = "jwt_token")
    private String JwtToken;

    public AccountJWTDTO(Account account, String jwtToken) {
        this.account = account;
        JwtToken = jwtToken;
    }

    public AccountJWTDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }
}
