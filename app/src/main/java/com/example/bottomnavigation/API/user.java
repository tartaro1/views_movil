package com.example.bottomnavigation.API;

public class user {
    public user(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    private int id;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;
}
