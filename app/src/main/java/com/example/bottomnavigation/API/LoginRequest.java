package com.example.bottomnavigation.API;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    @SerializedName("Correo")
    private String Correo;

    @SerializedName("Contrasena")
    private String Contrasena;

    public LoginRequest(String correo, String contrasena) {
        Correo = correo;
        Contrasena = contrasena;
    }
    // Getters y setters
}
