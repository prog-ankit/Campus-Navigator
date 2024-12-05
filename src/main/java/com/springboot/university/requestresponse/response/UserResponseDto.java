package com.springboot.university.requestresponse.response;

public class UserResponseDto {
    private String id;
    private String username;
    private String roles;
    private String message;

    public UserResponseDto(String id, String username, String roles, String message) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.message = message;
    }

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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
