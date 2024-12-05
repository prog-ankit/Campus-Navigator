package com.springboot.university.requestresponse.response;

public class LoginResponseDto {
    private String token;
    private String expiry;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String token, String expiry) {
        this.token = token;
        this.expiry = expiry;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
