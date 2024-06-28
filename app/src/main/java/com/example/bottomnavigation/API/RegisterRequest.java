package com.example.bottomnavigation.API;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    public RegisterRequest(String name, int cedula, String telefono, String direccion, String email, String password) {
        this.name = name;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
    }

    @SerializedName("Nombre")
    private String name;

    @SerializedName("Celular")
    private String telefono;

    @SerializedName("Cedula")
    private int cedula;

    @SerializedName("Direccion")
    private String direccion;

    @SerializedName("Correo")
    private String email;

    @SerializedName("Contrasena")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

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




}


