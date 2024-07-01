package com.example.bottomnavigation.API;

public class LoginResponse {
    private boolean success;
    private String message;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    private int role;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    // Getters y setters

    public boolean isSuccess() {
        return success;
    }
}
