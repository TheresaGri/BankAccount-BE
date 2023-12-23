package com.bankApplication.BankAccountBE.models;

public class LoginResponseDTO {
    private String username;
    private long userId;
    private String jwt;

    public LoginResponseDTO(String username, long userId, String jwt) {
        this.username = username;
        this.userId = userId;
        this.jwt = jwt;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
